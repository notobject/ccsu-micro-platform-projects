package cn.ccsu.notify.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author hangs.zhang
 * @date 2018/12/9
 * *****************
 * function:
 */
public interface NotifyBarista {

    String OUTPUT_CHANNEL = "notify_output_channel";

    String INPUT_CHANNEL = "notify_input_channel";

    @Output(NotifyBarista.OUTPUT_CHANNEL)
    MessageChannel output();

    @Input(NotifyBarista.INPUT_CHANNEL)
    SubscribableChannel input();

}
