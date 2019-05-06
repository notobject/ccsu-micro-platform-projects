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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
        return modelAndView;
    }

    @PostMapping("/new")
    @ResponseBody
    public BaseRes newService(HttpSession session, ServiceInfo serviceInfo) {

        // TODO 从会话中取出用户
        UserInfo user = (UserInfo) session.getAttribute("user");

        serviceInfo.setCreateTime(new Date(System.currentTimeMillis()));
        serviceInfo.setCreator("龙杜平");
        serviceInfo.setCreatorId(1);
        // 执行创建任务，返回一个任务ID， 前端再根据任务ID去查询任务的执行进度
        TaskInfo taskInfo = microServiceService.newService(user, serviceInfo);
        if (null == taskInfo) {
            return Resp.failed(10001, "创建服务失败");
        }
        return Resp.success(taskInfo);
    }

    @GetMapping("/process")
    @ResponseBody
    public String process(String taskId) {
        return TaskManagement.getInstance().getCurrentState(taskId);
    }
}
