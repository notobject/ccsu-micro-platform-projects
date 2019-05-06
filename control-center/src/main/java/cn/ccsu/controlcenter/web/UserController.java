/*
 * Created by Long Duping
 * Date 2019-03-27 19:08
 */
package cn.ccsu.controlcenter.web;

import cn.ccsu.controlcenter.pojo.UserInfo;
import cn.ccsu.controlcenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * 云控中心 用户管理
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public ModelAndView index(HttpSession session, ModelAndView modelAndView) {
        if (session.getAttribute("user") == null) {
            return new ModelAndView("redirect:/user/login");
        }
        modelAndView.setViewName("views/user");
        modelAndView.addObject("users", userService.getUsers());
        return modelAndView;
    }

    @GetMapping("/login")
    public String loginForm() {
        return "views/login";
    }

    @PostMapping("/login")
    public String login(String username, String password, HttpSession session) {
        UserInfo userInfo = userService.login(username, password);
        if (userInfo != null) {
            session.setAttribute("user", userInfo);
            return "redirect:/";
        } else {
            return "redirect:/error?msg=access rejected!";
        }
    }

    @PostMapping("/update")
    public String add(UserInfo user) {
        if (userService.add(user)) {
            return "redirect:/user";
        } else {
            return "redirect:/error?msg=create user failed.";
        }
    }

    @GetMapping("/delete")
    public String delete(@RequestParam int id) {
        if (userService.delete(id)) {
            return "redirect:/user";
        } else {
            return "redirect:/error?msg=delete failed.";
        }

    }
}
