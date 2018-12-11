package cn.ccsu.notify.stream.receiver;

import cn.ccsu.notify.stream.Barista;
import cn.ccsu.notify.websocket.NotifyWebSocketServer;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hangs.zhang
 * @date 2018/12/9
 * *****************
 * function:
 */
@Slf4j
@Service
@EnableBinding(Barista.class)
public class RabbitMQReceiver {

    @StreamListener(Barista.INPUT_CHANNEL)
    public void receiver(Message<String> message) throws Exception {
        Channel channel = (Channel) message.getHeaders().get(AmqpHeaders.CHANNEL);
        Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        String payload = message.getPayload();
        List<Integer> list = JSON.parseObject(payload, new TypeReference<List<Integer>>() {});
        // websocket 推送通知
        list.forEach(e -> NotifyWebSocketServer.sendInfo("您有新的消息", e));
        log.info("Input Stream 接收消息 : {}", payload);
        channel.basicAck(deliveryTag, false);
    }

}
