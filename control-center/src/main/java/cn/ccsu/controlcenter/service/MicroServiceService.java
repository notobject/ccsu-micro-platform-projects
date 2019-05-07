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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
public class MicroServiceService {

    @Autowired
    private ServiceDAO serviceDAO;

    @Autowired
    MQSender sender;

    public TaskInfo build(UserInfo user, ServiceInfo serviceInfo) {

        // 创建 编译任务
        TaskInfo task = TaskManagement.getInstance().newTask(TaskManagement.TASK_TYPE_BUILD, user == null ? 0 : user.getId());
        if (task == null) {
            log.error("create task failed.");
            return null;
        }
        log.info("task created {}", task);
        // 将 serviceInfo 插入数据库
        serviceInfo.setCreatorId(user.getId());
        serviceInfo.setCreator(user.getName());
        serviceInfo.setTaskId(task.getId());
        serviceInfo.setIpAddress(task.getMachine().getIp());
        serviceInfo.setCreateTime(new Date(System.currentTimeMillis()));
        serviceInfo.setStatus("building");
        int n = serviceDAO.insert(serviceInfo);
        if (n != 1) {
            log.error("insert service failed.");
            return null;
        }
        task.setServiceId(serviceInfo.getId());
        ScriptBuilder builder = new ScriptBuilder();
        builder.action(task.getAction())
                .task(task.getId())
                .param("work_dir", "/tmp/build")
                .param("repo_url", serviceInfo.getRepoUrl())
                .param("service_name", serviceInfo.getServiceName())
                .param("version", serviceInfo.getVersionName())
                .command("$work_dir", "rm -rf ccsu-micro-platform-projects")
                .command("$work_dir", "git clone $repo_url")
                .command("$work_dir/ccsu-micro-platform-projects/common-code", "mvn install")
                .command("$work_dir/ccsu-micro-platform-projects/$service_name", serviceInfo.getBuildCmd())
                .command("$work_dir/ccsu-micro-platform-projects/$service_name", "docker build -t notobject/$service_name:$version .")
                .command("$work_dir/ccsu-micro-platform-projects/$service_name", "docker push notobject/$service_name:$version");
        sender.send(task.getMachine().getQueue(), builder.build());
        return task;
    }

    public TaskInfo start(Integer serviceId) {
        ServiceInfo serviceInfo = serviceDAO.selectByPrimaryKey(serviceId);
        TaskInfo task = TaskManagement.getInstance().newTask(TaskManagement.TASK_TYPE_DEPLOY, serviceInfo.getCreatorId());
        int port = serviceInfo.getServicePort();
        ScriptBuilder builder = new ScriptBuilder();
        builder.action("start")
                .task(task.getId())
                .param("service_name", serviceInfo.getServiceName())
                .param("version", serviceInfo.getVersionName())
                .param("port", port <= 20 ? 20000 + new Random(System.currentTimeMillis()).nextInt(10000) : port)
                .param("options", "--server.port=$port --eureka.instance.ip-address=$HOST --eureka.client.service-url.defaultZone=$REGISTER_CENTER")
                .command(".", "docker pull notobject/$service_name:$version")
                .command(".", "docker rm -f $service_name")
                .command(".", "docker run -d --name $service_name -p $port:$port --restart=always notobject/$service_name:$version $options");
        sender.send(task.getMachine().getQueue(), builder.build());
        return task;
    }

    public TaskInfo stop(Integer serviceId) {
        ServiceInfo serviceInfo = serviceDAO.selectByPrimaryKey(serviceId);
        TaskInfo task = TaskManagement.getInstance().newTask(TaskManagement.TASK_TYPE_DEPLOY, serviceInfo.getCreatorId());
        ScriptBuilder builder = new ScriptBuilder();
        builder.action("stop")
                .task(task.getId())
                .param("service_name", serviceInfo.getServiceName())
                .command(".", "docker stop $service_name");
        sender.send(task.getMachine().getQueue(), builder.build());
        return task;
    }

    public TaskInfo restart(Integer serviceId) {
        ServiceInfo serviceInfo = serviceDAO.selectByPrimaryKey(serviceId);
        TaskInfo task = TaskManagement.getInstance().newTask(TaskManagement.TASK_TYPE_DEPLOY, serviceInfo.getCreatorId());
        ScriptBuilder builder = new ScriptBuilder();
        builder.action("restart")
                .task(task.getId())
                .param("service_name", serviceInfo.getServiceName())
                .command(".", "docker stop $service_name")
                .command(".", "sleep 3")
                .command(".", "docker start $service_name");
        sender.send(task.getMachine().getQueue(), builder.build());
        return task;
    }

    public List<ServiceInfo> getAll() {
        return serviceDAO.selectAll();
    }

    public void updateStatus(Integer sid, String action, String taskId) {
        ServiceInfo serviceInfo = serviceDAO.selectByPrimaryKey(sid);
        serviceInfo.setStatus(action);
        serviceInfo.setTaskId(taskId);
        serviceDAO.updateByPrimaryKey(serviceInfo);
    }

    public ServiceInfo getOne(Integer sid) {
        return serviceDAO.selectByPrimaryKey(sid);
    }
}
