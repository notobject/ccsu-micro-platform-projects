/*
 * Created by Long Duping
 * Date 2019-03-31 15:59
 */
package cn.ccsu.controlcenter.service;

import cn.ccsu.controlcenter.dao.AuditDAO;
import cn.ccsu.controlcenter.dao.ServiceDAO;
import cn.ccsu.controlcenter.dao.ServiceMachineDAO;
import cn.ccsu.controlcenter.mq.MQSender;
import cn.ccsu.controlcenter.pojo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
public class MicroServiceService {

    @Autowired
    private ServiceDAO serviceDAO;
    @Autowired
    private ServiceMachineDAO serviceMachineDAO;
    @Autowired
    private MQSender sender;

    public TaskInfo build(UserInfo user, ServiceInfo serviceInfo) {
        ServiceInfo condition = new ServiceInfo();
        condition.setServiceName(serviceInfo.getServiceName());
        condition.setVersionName(serviceInfo.getVersionName());
        ServiceInfo selectOne = serviceDAO.selectOne(condition);
        if (selectOne != null) {
            serviceDAO.delete(selectOne);
        }
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
        serviceInfo.setCreateTime(new Date(System.currentTimeMillis()));
        serviceDAO.insert(serviceInfo);
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
                .command("$work_dir/ccsu-micro-platform-projects/$service_name", "docker build -t 172.20.10.5:5000/$service_name:$version .")
                .command("$work_dir/ccsu-micro-platform-projects/$service_name", "docker push 172.20.10.5:5000/$service_name:$version");
        sender.send(task.getMachine().getQueue(), builder.build());
        return task;
    }

    public TaskInfo start(ServiceInfo serviceInfo) {
        int port = serviceInfo.getServicePort();
        List<ServiceMachineInfo> serviceMachineInfos = this.getServiceMachines(serviceInfo.getId());
        String[] excepts = new String[serviceMachineInfos.size()];
        int i = 0;
        for (ServiceMachineInfo smi : serviceMachineInfos) {
            excepts[i++] = smi.getMid();
        }
        TaskInfo task = TaskManagement.getInstance().newTask(TaskManagement.TASK_TYPE_DEPLOY, serviceInfo.getCreatorId(), excepts, null);
        if (task == null) {
            log.error("create start task failed.");
            return null;
        }
        ScriptBuilder builder = new ScriptBuilder();
        builder.action("start")
                .task(task.getId())
                .param("service_name", serviceInfo.getServiceName())
                .param("host", task.getMachine().getIp())
                .param("register_center", "http://172.20.10.6:8761/eureka,http://172.20.10.7:8761/eureka")
                .param("version", serviceInfo.getVersionName())
                .param("port", port <= 20 ? 20000 + new Random(System.currentTimeMillis()).nextInt(10000) : port)
                .param("options", "--server.port=$port --eureka.instance.ip-address=$host --eureka.client.service-url.defaultZone=$register_center")
                .command(".", "docker pull 172.20.10.5:5000/$service_name:$version")
                //.command(".", "docker rm -f $service_name || echo $service_name")
                .command(".", "docker run -d --name $service_name -p $port:$port 172.20.10.5:5000/$service_name:$version $options");
        sender.send(task.getMachine().getQueue(), builder.build());
        ServiceMachineInfo serviceMachineInfo = new ServiceMachineInfo();
        serviceMachineInfo.setMid(task.getMachine().getId());
        serviceMachineInfo.setSid(serviceInfo.getId());
        serviceMachineInfo.setCreateTime(new Date(System.currentTimeMillis()));
        serviceMachineInfo.setCreator(serviceInfo.getCreator());
        serviceMachineInfo.setPort(port);
        serviceMachineInfo.setStatus("Running");
        serviceMachineDAO.insert(serviceMachineInfo);
        return task;
    }

    public TaskInfo stop(ServiceInfo serviceInfo, MachineInfo machineInfo) {
        TaskInfo task = TaskManagement.getInstance().newTask(TaskManagement.TASK_TYPE_DEPLOY, serviceInfo.getCreatorId(), machineInfo);
        ScriptBuilder builder = new ScriptBuilder();
        builder.action("stop")
                .task(task.getId())
                .param("service_name", serviceInfo.getServiceName())
                .command(".", "docker stop $service_name");
        sender.send(task.getMachine().getQueue(), builder.build());
        return task;
    }

    public TaskInfo restart(ServiceInfo serviceInfo, MachineInfo machineInfo) {
        TaskInfo task = TaskManagement.getInstance().newTask(TaskManagement.TASK_TYPE_DEPLOY, serviceInfo.getCreatorId(), machineInfo);
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

        serviceDAO.updateByPrimaryKey(serviceInfo);
    }

    public ServiceInfo getOne(Integer sid) {
        return serviceDAO.selectByPrimaryKey(sid);
    }

    public List<ServiceMachineInfo> getServiceMachines(Integer sid) {
        Example example = new Example(ServiceMachineInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andCondition("sid = " + sid);
        return serviceMachineDAO.selectByExample(example);
    }

}
