/*
 * Created by Long Duping
 * Date 2019-04-13 15:34
 */
package cn.ccsu.controlcenter.mq;

import cn.ccsu.controlcenter.pojo.AckInfo;
import cn.ccsu.controlcenter.pojo.MachineInfo;
import cn.ccsu.controlcenter.pojo.ServiceInfo;
import cn.ccsu.controlcenter.pojo.UserInfo;
import cn.ccsu.controlcenter.service.MachineManagement;
import cn.ccsu.controlcenter.service.MicroServiceService;
import cn.ccsu.controlcenter.service.TaskManagement;
import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AgentReportListener implements ChannelAwareMessageListener {
    public static final String ACTION_HEART_BEAT = "heartbeat";
    public static final String ACTION_ACK = "ack";

    @Autowired
    private MicroServiceService serviceService;


    @Override
    public void onMessage(Message message, Channel channel) {
        JSONObject msg = (JSONObject) JSONObject.parse(message.getBody());
        String action = msg.getString("action");
        if (ACTION_HEART_BEAT.equals(action)) {
            MachineInfo machineInfo = JSONObject.parseObject(msg.getString("data"), MachineInfo.class);
            log.info("heartbeat {}", machineInfo);
            MachineManagement.getInstance().update(machineInfo);
            // TODO 测试需要....
            UserInfo user = new UserInfo();
            user.setId(1);
            ServiceInfo service = new ServiceInfo();
            service.setCreator("me");
            service.setInstanceNum(1);
            service.setRepoUrl("https://github.com/notobject/ccsu-micro-platform-projects.git");
            service.setCreatorId(1);
            service.setServiceName("ccsu-register-server");
            service.setVersionName("1.0.0.RELEASE");
            service.setServicePort(8761);
            serviceService.newService(user, service);
        } else if (ACTION_ACK.equals(action)) {
            AckInfo ackInfo = JSONObject.parseObject(msg.getString("data"), AckInfo.class);
            log.info("ack {}", ackInfo);
            TaskManagement.getInstance().doProcess(ackInfo);
        } else {
            log.error("not support action={}, data={}", action, msg.getString("data"));
        }
    }
}
