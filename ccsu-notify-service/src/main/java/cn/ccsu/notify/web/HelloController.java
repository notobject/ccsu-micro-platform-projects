package cn.ccsu.notify.web;

import cn.ccsu.notify.stream.sender.RabbitMQSender;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author hangs.zhang
 * @date 2018/12/11
 * *****************
 * function:
 */
@RestController
public class HelloController {

    @Autowired
    private RabbitMQSender rabbitMQSender;

    @GetMapping("/push")
    public String push() {
        List<Integer> userIds = Lists.newArrayList(1, 2, 3, 4, 5);
        rabbitMQSender.sendMessage(JSON.toJSONString(userIds), null);
        return "success";
    }

}
