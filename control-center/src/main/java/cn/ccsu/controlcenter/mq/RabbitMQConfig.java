/*
 * Created by Long Duping
 * Date 2019-04-13 14:50
 */
package cn.ccsu.controlcenter.mq;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class RabbitMQConfig {

    public static final String SUFFIX = "micro-platform.";
    public static final String TOPIC_EXCHANGE = SUFFIX + "topic";


    /**
     * 动态绑定消息队列到相应的exchange上
     *
     * @param connectionFactory
     * @return
     */
    @Bean
    RabbitAdmin rabbitAdmin(final ConnectionFactory connectionFactory) {
        final RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        String[] queues = {SUFFIX + "control-center"};
        // rabbitAdmin.deleteExchange(TOPIC_EXCHANGE);
        TopicExchange topicExchange = new TopicExchange(TOPIC_EXCHANGE, true, false);
        rabbitAdmin.declareExchange(topicExchange);
        for (int i = 0; i < queues.length; i++) {
            Queue queue = new Queue(queues[i]);
            rabbitAdmin.deleteQueue(queues[i]);
            rabbitAdmin.declareQueue(queue);
            Binding binding = BindingBuilder.bind(queue).to(topicExchange).with(queues[i]);
            rabbitAdmin.declareBinding(binding);
        }
        return rabbitAdmin;
    }

    @Bean
    public MQSender mqSender(AmqpTemplate amqpTemplate) {
        return new MQSender(amqpTemplate);
    }

    @Bean
    public SimpleMessageListenerContainer mqMessageContainer(AgentReportListener listener, ConnectionFactory connectionFactory) throws AmqpException, IOException {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setQueueNames(SUFFIX + "control-center");
        container.setExposeListenerChannel(true);
        container.setAcknowledgeMode(AcknowledgeMode.AUTO);
        container.setMessageListener(listener);
        return container;
    }

}
