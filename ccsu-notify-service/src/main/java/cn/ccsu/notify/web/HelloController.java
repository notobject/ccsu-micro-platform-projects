package cn.ccsu.notify.web;

import cn.ccsu.notify.enums.NotifyType;
import cn.ccsu.notify.enums.SystemNotifyType;
import cn.ccsu.notify.stream.message.MailMessage;
import cn.ccsu.notify.stream.message.NotifyMessage;
import cn.ccsu.notify.stream.sender.MailMQSender;
import cn.ccsu.notify.stream.sender.NotifyMQSender;
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
    private NotifyMQSender notifyMQSender;

    @Autowired
    private MailMQSender mailMQSender;

    @GetMapping("/system/push")
    public String system() {
        List<Integer> userIds = Lists.newArrayList(1);
        NotifyMessage notifyMessage = new NotifyMessage();
        // 测试使用userId进行push
        notifyMessage.setUserIds(userIds);
        // 发送的消息
        NotifyMessage.Notify notify = new NotifyMessage.Notify();
        NotifyMessage.SystemNotify systemNotify = new NotifyMessage.SystemNotify();
        systemNotify.setSystemNotifyType(SystemNotifyType.activity.toString());
        systemNotify.setActivityName("云计数大赛");
        notify.setSystemNotify(systemNotify);
        notify.setNotifyType(NotifyType.system.toString());
        notify.setMessage("您已经报名成功");
        notify.setSendTime(new Date());
        notifyMessage.setNotify(notify);
        // 发送
        notifyMQSender.sendMessage(notifyMessage, null);
        return "success";
    }

    @GetMapping("/comment/push")
    public String comment() {
        List<Integer> userIds = Lists.newArrayList(10);
        NotifyMessage notifyMessage = new NotifyMessage();
        // 测试使用userId进行push
        notifyMessage.setUserIds(userIds);
        // 发送的消息
        NotifyMessage.Notify notify = new NotifyMessage.Notify();
        NotifyMessage.NoteCommentNotify noteCommentNotify = new NotifyMessage.NoteCommentNotify();
        noteCommentNotify.setAuthorId(10);
        noteCommentNotify.setNoteId(10);
        notify.setNoteCommentNotify(noteCommentNotify);
        notify.setNotifyType(NotifyType.comment.toString());
        notify.setMessage("你说的对");
        notify.setSendTime(new Date());
        notifyMessage.setNotify(notify);
        // 发送
        notifyMQSender.sendMessage(notifyMessage, null);
        return "success";
    }

    @GetMapping("/mail/send")
    public String sendMail() {
        MailMessage mailMessage = new MailMessage();
        mailMessage.setDestination("m18374976843@163.com");
        mailMessage.setSubject("这是一封测试邮件");
        mailMessage.setText("这是测试邮件的内容");
        mailMQSender.sendMessage(mailMessage, null);
        return "success";
    }

}
