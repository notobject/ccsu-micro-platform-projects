package cn.ccsu.demo.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hangs.zhang
 * @date 2018/12/4
 * *****************
 * function:
 */
@RefreshScope
@RestController
public class HelloController {

    @Value("${tag: unknow}")
    private String tag;

    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }

    @GetMapping("/tag")
    public String tag() {
        return tag;
    }

}
