/*
 * Created by Long Duping
 * Date 2019-04-13 17:42
 */
package cn.ccsu.controlcenter.mq;

import cn.ccsu.controlcenter.pojo.TaskInfo;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;

@Slf4j
public class MQSender {
    private AmqpTemplate amqpTemplate;

    public MQSender(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void send(TaskInfo taskInfo, JSONObject data) {
        if (null == data) {
            data = new JSONObject();
        }
        data.put("action", taskInfo.getAction());
        log.info("send " + data.toString());
        amqpTemplate.convertAndSend("micro-platform.topic", taskInfo.getMachine().getQueue(), data.toString());
    }
}
