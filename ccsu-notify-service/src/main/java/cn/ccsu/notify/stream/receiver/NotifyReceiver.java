package cn.ccsu.notify.stream.receiver;

import cn.ccsu.notify.stream.Barista;
import cn.ccsu.notify.stream.message.NotifyMessage;
import cn.ccsu.notify.utils.BaseEntityUtil;
import cn.ccsu.notify.websocket.NotifyWebSocketServer;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author hangs.zhang
 * @date 2018/12/9
 * *****************
 * function:
 */
@Slf4j
@Service
@EnableBinding(Barista.class)
public class NotifyReceiver {

    @StreamListener(Barista.INPUT_CHANNEL)
    public void receiver(Message<NotifyMessage> message) throws Exception {
        Channel channel = (Channel) message.getHeaders().get(AmqpHeaders.CHANNEL);
        Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        NotifyMessage notifyMessage = message.getPayload();
        log.info("notify : {}", notifyMessage);
        // TODO: 2018/12/11 通知入库
        List<Integer> list = notifyMessage.getUserIds();
        if (list == null) log.info("通知的userIds为null");

        // websocket 推送通知
        JSONObject pushJson = new JSONObject();
        pushJson.put("errcode", 0);
        pushJson.put("errmsg", "having new notify");
        list.forEach(e -> NotifyWebSocketServer.sendInfo(pushJson.toJSONString(), e));
        channel.basicAck(deliveryTag, false);
    }

}
