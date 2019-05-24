/*
 * Created by Long Duping
 * Date 2019-03-27 19:00
 */
package cn.ccsu.controlcenter.web;

import cn.ccsu.controlcenter.dao.AuditDAO;
import cn.ccsu.controlcenter.dao.ServiceDAO;
import cn.ccsu.controlcenter.dao.ServiceMachineDAO;
import cn.ccsu.controlcenter.pojo.*;
import cn.ccsu.controlcenter.service.MachineManagement;
import cn.ccsu.controlcenter.service.MicroServiceService;
import cn.ccsu.controlcenter.service.TaskManagement;
import cn.ccsu.controlcenter.util.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 服务管理：
 * 上线，启动，停止，重启，扩容，回滚
 */
@Controller
@RequestMapping("/service")
public class MicroServiceController {

    @Autowired
    private MicroServiceService microServiceService;
    @Autowired
    private AuditDAO auditDAO;
    @Autowired
    private ServiceDAO serviceDAO;
    @Autowired
    private ServiceMachineDAO serviceMachineDAO;

    @GetMapping()
    public ModelAndView form(HttpSession session, ModelAndView modelAndView, Integer sid) {
        if (session.getAttribute("user") == null) {
            return new ModelAndView("redirect:/user/login");
        }
        if (sid != null && sid > 0) {
            modelAndView.setViewName("views/service_detail");
            ServiceInfo service = microServiceService.getOne(sid);
            modelAndView.addObject("service", service);
            List<Map<String, Object>> infos = new ArrayList<>();
            List<ServiceMachineInfo> serviceMachines = microServiceService.getServiceMachines(sid);
            Map<String, Object> map;
            for (ServiceMachineInfo smi : serviceMachines) {
                map = new HashMap<>();
                MachineInfo machine = MachineManagement.getInstance().getByMid(smi.getMid());
                if (machine == null) {
                    continue;
                }
                map.put("id", smi.getId());
                map.put("createTime", smi.getCreateTime());
                map.put("status", smi.getStatus());
                map.put("address", machine.getIp() + ":" + smi.getPort());
                map.put("name", service.getServiceName());
                map.put("version", service.getVersionName());
                map.put("creator", smi.getCreator());
                infos.add(map);
            }
            modelAndView.addObject("infos", infos);
            return modelAndView;
        }
        modelAndView.setViewName("views/service");
        modelAndView.addObject("serviceList", microServiceService.getAll());
        return modelAndView;
    }

    @PostMapping("/build")
    @ResponseBody
    public BaseRes buildService(HttpSession session, ServiceInfo serviceInfo) {
        UserInfo user = (UserInfo) session.getAttribute("user");
        // 执行创建任务，返回一个任务ID， 前端再根据任务ID去查询任务的执行进度
        TaskInfo taskInfo = microServiceService.build(user, serviceInfo);
        if (null == taskInfo) {
            auditDAO.insert(new AuditInfo(user.getName(), "服务构建", serviceInfo.getServiceName() + ":" + serviceInfo.getVersionName(), false));
            return Resp.failed(10001, "创建服务失败");
        }
        auditDAO.insert(new AuditInfo(user.getName(), "服务构建", serviceInfo.getServiceName() + ":" + serviceInfo.getVersionName(), true));
        return Resp.success(taskInfo);
    }

    @PostMapping("/manage")
    @ResponseBody
    public BaseRes manageService(HttpSession session, @RequestParam String action, @RequestParam Integer sid) {
        UserInfo user = (UserInfo) session.getAttribute("user");
        TaskInfo taskInfo;
        if ("start".equals(action)) {
            ServiceInfo serviceInfo = serviceDAO.selectByPrimaryKey(sid);
            taskInfo = microServiceService.start(serviceInfo);
            if (null == taskInfo) {
                return Resp.failed(-1, "启动服务失败");
            }
        } else if ("stop".equals(action)) {
            ServiceMachineInfo serviceMachineInfo = serviceMachineDAO.selectByPrimaryKey(sid);
            ServiceInfo serviceInfo = serviceDAO.selectByPrimaryKey(serviceMachineInfo.getSid());
            MachineInfo machineInfo = MachineManagement.getInstance().getByMid(serviceMachineInfo.getMid());
            taskInfo = microServiceService.stop(serviceInfo, machineInfo);
            if (null == taskInfo) {
                return Resp.failed(-1, "停止服务失败");
            }
            serviceMachineInfo.setStatus("Stopping");
            serviceMachineDAO.updateByPrimaryKey(serviceMachineInfo);
        } else if ("restart".equals(action)) {
            ServiceMachineInfo serviceMachineInfo = serviceMachineDAO.selectByPrimaryKey(sid);
            ServiceInfo serviceInfo = serviceDAO.selectByPrimaryKey(serviceMachineInfo.getSid());
            MachineInfo machineInfo = MachineManagement.getInstance().getByMid(serviceMachineInfo.getMid());
            taskInfo = microServiceService.restart(serviceInfo, machineInfo);
            if (null == taskInfo) {
                return Resp.failed(-1, "重启服务失败");
            }
        } else {
            return Resp.failed(-1, "错误指令");
        }
        auditDAO.insert(new AuditInfo(user.getName(), "服务管理-" + action, taskInfo.getMachine().getIp(), true));
        //microServiceService.updateStatus(sid, action, taskInfo.getId());
        return Resp.success(taskInfo);
    }

    @GetMapping("/process")
    @ResponseBody
    public String process(String taskId) {
        String msg = TaskManagement.getInstance().getCurrentState(taskId);
        return msg.replaceAll("\n", "<br/>").replaceAll("\r\n", "<br/>");
    }

    @GetMapping("/status")
    public ModelAndView status(@RequestParam String taskId, ModelAndView modelAndView) {
        modelAndView.addObject("taskId", taskId);
        modelAndView.setViewName("views/status");
        return modelAndView;
    }
}
