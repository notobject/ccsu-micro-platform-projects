/*
 * Created by Long Duping
 * Date 2019-03-27 19:00
 */
package cn.ccsu.controlcenter.web;

import cn.ccsu.controlcenter.service.TaskManagement;
import cn.ccsu.controlcenter.pojo.BaseRes;
import cn.ccsu.controlcenter.pojo.ServiceInfo;
import cn.ccsu.controlcenter.pojo.TaskInfo;
import cn.ccsu.controlcenter.pojo.UserInfo;
import cn.ccsu.controlcenter.service.MicroServiceService;
import cn.ccsu.controlcenter.util.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * 服务管理：
 * 上线，启动，停止，重启，扩容，回滚
 */
@Controller
@RequestMapping("/service")
public class MicroServiceController {

    @Autowired
    private MicroServiceService microServiceService;

    @GetMapping()
    public ModelAndView form(HttpSession session, ModelAndView modelAndView) {
        if (session.getAttribute("user") == null) {
            return new ModelAndView("redirect:/user/login");
        }

        modelAndView.setViewName("views/service");
        modelAndView.addObject("serviceList", microServiceService.getAll());
        return modelAndView;
    }

    @PostMapping("/build")
    @ResponseBody
    public BaseRes buildService(HttpSession session, ServiceInfo serviceInfo) {

        // TODO 从会话中取出用户
        UserInfo user = (UserInfo) session.getAttribute("user");
        // 执行创建任务，返回一个任务ID， 前端再根据任务ID去查询任务的执行进度
        TaskInfo taskInfo = microServiceService.build(user, serviceInfo);
        if (null == taskInfo) {
            return Resp.failed(10001, "创建服务失败");
        }
        return Resp.success(taskInfo);
    }

    @GetMapping("/manage")
    public String manageService(@RequestParam String action, @RequestParam Integer sid) {
        TaskInfo taskInfo;
        if ("start".equals(action)) {
            taskInfo = microServiceService.start(sid);
            if (null == taskInfo) {
                return "redirect:/service?errmsg=创建服务失败";
            }
        } else if ("stop".equals(action)) {
            taskInfo = microServiceService.stop(sid);
            if (null == taskInfo) {
                return "redirect:/service?errmsg=停止服务失败";
            }
        } else if ("restart".equals(action)) {
            taskInfo = microServiceService.restart(sid);
            if (null == taskInfo) {
                return "redirect:/service?errmsg=重启服务失败";
            }
        } else {
            return "redirect:/service?errmsg=错误指令：" + action;
        }
        microServiceService.updateStatus(sid, action, taskInfo.getId());
        return "redirect:/service";
    }

    @GetMapping("/process")
    @ResponseBody
    public String process(String taskId) {
        return TaskManagement.getInstance().getCurrentState(taskId);
    }

    @GetMapping("/status")
    public ModelAndView status(@RequestParam String taskId, ModelAndView modelAndView) {
        modelAndView.addObject("taskId", taskId);
        modelAndView.setViewName("views/status");
        return modelAndView;
    }
}
