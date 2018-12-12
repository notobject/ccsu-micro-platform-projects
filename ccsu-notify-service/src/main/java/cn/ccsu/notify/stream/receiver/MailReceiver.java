package cn.ccsu.notify.stream.receiver;

import cn.ccsu.notify.stream.NotifyBarista;
import cn.ccsu.notify.stream.MailBarista;
import cn.ccsu.notify.stream.message.MailMessage;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

/**
 * @author hangs.zhang
 * @date 2018/12/9
 * *****************
 * function:
 */
@Slf4j
@Service
@EnableBinding(MailBarista.class)
public class MailReceiver {

    @Value("${spring.mail.username}")
    private String fromMail;

    @Autowired
    private JavaMailSender mailSender;

    @StreamListener(MailBarista.MAIL_INPUT_CHANNEL)
    public void receiver(Message<MailMessage> message) throws Exception {
        Channel channel = (Channel) message.getHeaders().get(AmqpHeaders.CHANNEL);
        Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        MailMessage mailMessage = message.getPayload();
        sendMail(mailMessage);
        log.info("mail : {}", mailMessage);
        channel.basicAck(deliveryTag, false);
    }

    private void sendMail(MailMessage mailMessage) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(fromMail);
        simpleMailMessage.setTo(mailMessage.getDestination());
        simpleMailMessage.setText(mailMessage.getText());
        simpleMailMessage.setSubject(mailMessage.getSubject());
        mailSender.send(simpleMailMessage);
    }

}
