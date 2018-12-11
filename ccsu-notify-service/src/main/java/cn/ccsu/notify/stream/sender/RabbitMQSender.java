package cn.ccsu.notify.stream.sender;

import cn.ccsu.notify.stream.Barista;
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
 * @date 2018/12/9
 * *****************
 * function:
 */
@Slf4j
@Service
@EnableBinding(Barista.class)
public class RabbitMQSender {

    @Autowired
    private Barista barista;

    public void sendMessage(Object message, Map<String, Object> properties) {
        MessageHeaders messageHeaders = new MessageHeaders(properties);
        Message msg = MessageBuilder.createMessage(message, messageHeaders);
        boolean isSend = barista.output().send(msg);
        log.info("发送数据 : {}, 发送状态 : {}", message, isSend);
    }

}
