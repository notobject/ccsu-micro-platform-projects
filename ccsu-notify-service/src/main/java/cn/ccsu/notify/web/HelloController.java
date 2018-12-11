package cn.ccsu.notify.web;

import cn.ccsu.notify.enums.NotifyType;
import cn.ccsu.notify.stream.message.NotifyMessage;
import cn.ccsu.notify.stream.sender.RabbitMQSender;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
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
        NotifyMessage notifyMessage = new NotifyMessage();
        // 测试使用userId进行push
        notifyMessage.setUserIds(userIds);
        // 发送的消息
        NotifyMessage.SystemNotify systemNotify = new NotifyMessage.SystemNotify();
        systemNotify.setType(NotifyType.activity.getCode());
        systemNotify.setActivityName("云计数大赛");
        systemNotify.setMessage("您已经报名成功");
        systemNotify.setSendTime(new Date());
        notifyMessage.setNotify(systemNotify);

        // 发送
        rabbitMQSender.sendMessage(notifyMessage, null);
        return "success";
    }

}
