package cn.ccsu.notify.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author hangs.zhang
 * @date 2018/12/12
 * *****************
 * function:
 */
public interface MailBarista {

    String MAIL_OUTPUT_CHANNEL = "mail_output_channel";

    String MAIL_INPUT_CHANNEL = "mail_input_channel";

    @Output(MailBarista.MAIL_OUTPUT_CHANNEL)
    MessageChannel output();

    @Input(MailBarista.MAIL_INPUT_CHANNEL)
    SubscribableChannel input();

}
