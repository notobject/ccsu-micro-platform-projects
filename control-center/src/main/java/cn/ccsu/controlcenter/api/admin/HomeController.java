/*
 * Created by Long Duping
 * Date 2019-03-27 20:17
 */
package cn.ccsu.controlcenter.api.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页
 */
@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "redirect:https://github.com/notobject/ccsu-micro-platform-projects";
    }
}
