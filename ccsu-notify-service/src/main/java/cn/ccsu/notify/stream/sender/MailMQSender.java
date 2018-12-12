package cn.ccsu.notify.stream.sender;

import cn.ccsu.notify.stream.MailBarista;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author hangs.zhang
 * @date 2018/12/12
 * *****************
 * function:
 */
@Slf4j
@Service
@EnableBinding(MailBarista.class)
public class MailMQSender {

    @Autowired
    private MailBarista mailBarista;

    public void sendMessage(Object message, Map<String, Object> properties) {
        MessageHeaders messageHeaders = new MessageHeaders(properties);
        Message msg = MessageBuilder.createMessage(message, messageHeaders);
        boolean isSend = mailBarista.output().send(msg);
        log.info("发送数据 : {}, 发送状态 : {}", message, isSend);
    }

}
