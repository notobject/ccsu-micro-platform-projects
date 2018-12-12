package cn.ccsu.notify.stream.receiver;

import cn.ccsu.notify.enums.NotifyType;
import cn.ccsu.notify.pojo.dto.NoteCommentNotifyDTO;
import cn.ccsu.notify.pojo.dto.SystemNotifyDTO;
import cn.ccsu.notify.service.NotifyService;
import cn.ccsu.notify.stream.NotifyBarista;
import cn.ccsu.notify.stream.message.NotifyMessage;
import cn.ccsu.notify.websocket.NotifyWebSocketServer;
import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
 @EnableBinding(NotifyBarista.class)
public class NotifyReceiver {

    @Autowired
    private NotifyService notifyService;

    @StreamListener(NotifyBarista.INPUT_CHANNEL)
    public void receiver(Message<NotifyMessage> message) throws Exception {
        Channel channel = (Channel) message.getHeaders().get(AmqpHeaders.CHANNEL);
        Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        NotifyMessage notifyMessage = message.getPayload();
        log.info("notify : {}", notifyMessage);
        // 入库
        stroageNotify(notifyMessage);
        // websocket 推送通知
        push(notifyMessage);
        channel.basicAck(deliveryTag, false);
    }

    private void push(NotifyMessage notifyMessage) {
        List<Integer> list = notifyMessage.getUserIds();
        if (list == null) log.info("通知的userIds为null");
        JSONObject pushJson = new JSONObject();
        pushJson.put("errcode", 0);
        pushJson.put("errmsg", "having new notify");
        list.forEach(e -> NotifyWebSocketServer.sendInfo(pushJson.toJSONString(), e));
    }

    private void stroageNotify(NotifyMessage notifyMessage) {
        NotifyMessage.Notify notify = notifyMessage.getNotify();
        String notifyType = notify.getNotifyType();
        if (NotifyType.comment.toString().equals(notifyType)) {
            NotifyMessage.NoteCommentNotify noteCommentNotify = notify.getNoteCommentNotify();
            NoteCommentNotifyDTO noteCommentNotifyDTO = new NoteCommentNotifyDTO();
            BeanUtils.copyProperties(noteCommentNotify, noteCommentNotifyDTO);
            BeanUtils.copyProperties(notify, noteCommentNotifyDTO);
            System.out.println(noteCommentNotifyDTO);
            notifyService.storageNotify(noteCommentNotifyDTO, notifyMessage.getUserIds(), NotifyType.getNotifyType(noteCommentNotifyDTO.getNotifyType()));
        } else {
            NotifyMessage.SystemNotify systemNotify = notify.getSystemNotify();
            SystemNotifyDTO systemNotifyDTO = new SystemNotifyDTO();
            BeanUtils.copyProperties(systemNotify, systemNotifyDTO);
            BeanUtils.copyProperties(notify, systemNotifyDTO);
            notifyService.storageNotify(systemNotifyDTO, notifyMessage.getUserIds(), NotifyType.getNotifyType(systemNotifyDTO.getNotifyType()));
        }
    }

}
