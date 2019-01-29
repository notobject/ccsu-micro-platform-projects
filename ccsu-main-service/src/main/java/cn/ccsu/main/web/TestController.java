package cn.ccsu.main.web;

import cn.ccsu.main.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hangs.zhang
 * @date 2019/1/29
 * *****************
 * function:
 */
@RestController
public class TestController {

    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("/test")
    public String test() {
        redisUtil.set("key", "value");
        String key = (String) redisUtil.get("key");
        System.out.println(key);
        return key;
    }

}
