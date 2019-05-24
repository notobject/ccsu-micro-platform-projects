/*
 * Created by Long Duping
 * Date 2019-03-27 19:08
 */
package cn.ccsu.controlcenter.web;

import cn.ccsu.controlcenter.dao.AuditDAO;
import cn.ccsu.controlcenter.pojo.AuditInfo;
import cn.ccsu.controlcenter.pojo.BaseRes;
import cn.ccsu.controlcenter.pojo.UserInfo;
import cn.ccsu.controlcenter.service.UserService;
import cn.ccsu.controlcenter.util.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 云控中心 用户管理
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuditDAO auditDAO;

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
    @ResponseBody
    public BaseRes login(String username, String password, HttpSession session, HttpServletRequest request) {
        UserInfo userInfo = userService.login(username, password);
        if (userInfo != null) {
            session.setAttribute("user", userInfo);
            auditDAO.insert(new AuditInfo(userInfo.getName(), "系统登录", "从[" + request.getRemoteHost() + "] 登录", true));
            return Resp.success("ok");
        } else {
            auditDAO.insert(new AuditInfo(username, "系统登录", "从[" + request.getRemoteHost() + "] 尝试登录", false));
            return Resp.failed(-1, "用户名或密码错误！");
        }
    }

    @GetMapping("logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/user/login";
    }

    @PostMapping("/update")
    public String add(HttpSession session, UserInfo newUser) {
        UserInfo user = (UserInfo) session.getAttribute("user");
        if (userService.add(newUser)) {
            auditDAO.insert(new AuditInfo(user.getName(), "添加用户", "用户 " + newUser.getUsername(), true));
            return "redirect:/user";
        } else {
            auditDAO.insert(new AuditInfo(user.getName(), "添加用户", "用户 " + newUser.getUsername(), false));
            return "redirect:/error?msg=create user failed.";
        }
    }

    @GetMapping("/delete")
    public String delete(HttpSession session, @RequestParam int id) {
        UserInfo user = (UserInfo) session.getAttribute("user");
        if (userService.delete(id)) {
            auditDAO.insert(new AuditInfo(user.getName(), "删除用户", "用户ID= " + id, true));
            return "redirect:/user";
        } else {
            auditDAO.insert(new AuditInfo(user.getName(), "删除用户", "用户ID= " + id, false));
            return "redirect:/error?msg=delete failed.";
        }

    }
}
