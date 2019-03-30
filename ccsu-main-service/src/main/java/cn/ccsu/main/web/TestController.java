package cn.ccsu.main.web;

import cn.ccsu.common.enums.NotifyType;
import cn.ccsu.common.enums.SystemNotifyType;
import cn.ccsu.common.message.NotifyMessage;
import cn.ccsu.main.stream.NotifyMQSender;
import cn.ccsu.main.utils.RedisUtil;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author hangs.zhang
 * @date 2019/1/29
 * *****************
 * function:
 */
@RestController
public class TestController {

    @Autowired
    private NotifyMQSender notifyMQSender;

    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("/test")
    public String test() {
        redisUtil.set("key", "value");
        String key = (String) redisUtil.get("key");
        System.out.println(key);
        return key;
    }

    @GetMapping("/userInfo")
    public String userInfo(@RequestBody String body) {
        return body;
    }

    @GetMapping("/push")
    public String push() {
        List<Integer> userIds = Lists.newArrayList(2);
        NotifyMessage notifyMessage = new NotifyMessage();
        notifyMessage.setUserIds(userIds);
        NotifyMessage.Notify notify = new NotifyMessage.Notify();
        NotifyMessage.SystemNotify systemNotify = new NotifyMessage.SystemNotify();
        systemNotify.setSystemNotifyType(SystemNotifyType.activity.toString());
        systemNotify.setActivityName("云计数大赛");
        notify.setSystemNotify(systemNotify);
        notify.setNotifyType(NotifyType.system.toString());
        notify.setMessage("您已经报名成功");
        notify.setSendTime(new Date());
        notifyMessage.setNotify(notify);
        notifyMQSender.sendMessage(notifyMessage, null);
        return "push success";
    }

}
