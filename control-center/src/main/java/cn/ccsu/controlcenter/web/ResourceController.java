/*
 * Created by Long Duping
 * Date 2019-03-27 19:04
 */
package cn.ccsu.controlcenter.web;

import cn.ccsu.controlcenter.service.MachineManagement;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * 资源配置管理
 * 机器资源配置，仓库资源配置
 */
@Controller
@RequestMapping("/resource")
public class ResourceController {
    @GetMapping()
    public ModelAndView index(HttpSession session, ModelAndView modelAndView) {
        if (session.getAttribute("user") == null) {
            return new ModelAndView("redirect:/user/login");
        }
        modelAndView.setViewName("views/resource");
        modelAndView.addObject("resources", MachineManagement.getInstance().getAll());
        return modelAndView;
    }

    @GetMapping("/staus")
    public ModelAndView status(String ip, ModelAndView modelAndView) {


        return modelAndView;
    }
}
