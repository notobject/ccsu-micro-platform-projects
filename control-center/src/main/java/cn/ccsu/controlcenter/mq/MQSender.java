/*
 * Created by Long Duping
 * Date 2019-04-13 17:42
 */
package cn.ccsu.controlcenter.mq;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;

@Slf4j
public class MQSender {


    private AmqpTemplate amqpTemplate;

    public MQSender(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }
    public void send(String queue, String msg){
        log.info("send -> {} {}",  queue, msg);
        amqpTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE, queue, msg);
    }
    public void send(String action, String queue, String msg) {
        JSONObject data = new JSONObject();
        data.put("action", action);
        data.put("data", msg);
        log.info("send -> {} {} {}", action, queue, data);
        amqpTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE, queue, data.toString());
    }
}
