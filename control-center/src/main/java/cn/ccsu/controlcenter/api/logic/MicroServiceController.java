/*
 * Created by Long Duping
 * Date 2019-03-27 19:00
 */
package cn.ccsu.controlcenter.api.logic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 服务管理：
 * 上线，启动，停止，重启，扩容，回滚
 */
@Controller
@RequestMapping("/ms")
public class MicroServiceController {

    @GetMapping("/{path}")
    public String form(@PathVariable String path) {
        return "/ms/" + path;
    }

}
