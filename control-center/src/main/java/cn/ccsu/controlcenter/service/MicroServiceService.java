/*
 * Created by Long Duping
 * Date 2019-03-31 15:59
 */
package cn.ccsu.controlcenter.service;

import cn.ccsu.controlcenter.dao.ServiceDAO;
import cn.ccsu.controlcenter.mq.MQSender;
import cn.ccsu.controlcenter.pojo.ServiceInfo;
import cn.ccsu.controlcenter.pojo.TaskInfo;
import cn.ccsu.controlcenter.pojo.UserInfo;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@Service
public class MicroServiceService {

    @Autowired
    private ServiceDAO serviceDAO;

    @Autowired
    MQSender sender;

    public TaskInfo newService(UserInfo user, ServiceInfo serviceInfo) {
        // 将 serviceInfo 插入数据库
        int n = serviceDAO.insert(serviceInfo);
        if (n != 1) {
            log.error("insert service failed.");
            return null;
        }
        // 创建 编译任务
        TaskInfo task = TaskManagement.getInstance().newTask(TaskManagement.TASK_TYPE_BUILD, user == null ? 0 : user.getId());
        if (task == null) {
            log.error("create task failed.");
            return null;
        }
        log.info("task created {}", task);

        // 构建 任务脚本
        JSONObject data = new JSONObject();
        data.put("taskId", task.getId());
        JSONArray cmds = new JSONArray();
        JSONObject params = new JSONObject();
        params.put("work_dir", "/tmp/build");
        params.put("repo_url", serviceInfo.getRepoUrl());
        params.put("service_name", serviceInfo.getServiceName());
        params.put("profile", "prod");
        params.put("version", serviceInfo.getVersionName());
        int port = serviceInfo.getServicePort();
        if (port <= 20) {
            port = 20000 + new Random(System.currentTimeMillis()).nextInt(10000);
        }
        params.put("port", port);
        params.put("options", "--server.port=$port --eureka.instance.ip-address=$HOST --eureka.client.service-url.defaultZone=$REGISTER_CENTER");
        JSONObject cmd = new JSONObject();
        cmd.put("dir", "$work_dir");
        cmd.put("cmd", "rm -rf ccsu-micro-platform-projects");
        cmds.add(cmd);
        cmd = new JSONObject();
        cmd.put("dir", "$work_dir");
        cmd.put("cmd", "git clone $repo_url");
        cmds.add(cmd);
        cmd = new JSONObject();
        cmd.put("dir", "$work_dir/ccsu-micro-platform-projects/common-code");
        cmd.put("cmd", "mvn install");
        cmds.add(cmd);
        cmd = new JSONObject();
        cmd.put("dir", "$work_dir/ccsu-micro-platform-projects/$service_name");
        cmd.put("cmd", "mvn clean package -P$profile");
        cmds.add(cmd);
        cmd = new JSONObject();
        cmd.put("dir", "$work_dir/ccsu-micro-platform-projects/$service_name");
        cmd.put("cmd", "mv target/*.jar /var/jars/");
        cmds.add(cmd);
        cmd = new JSONObject();
        cmd.put("dir", "/var/jars/");
        cmd.put("cmd", "docker run -d --name $service_name -p $port:$port -v /var/jars:/var/jars -v /var/logs:/var/logs --restart=always java:openjdk-8-jre-alpine java -jar /var/jars/$service_name-$version.jar $options");
        cmds.add(cmd);
        data.put("cmds", cmds);
        data.put("params", params);
        sender.send(task, data);

        return task;
    }
}
