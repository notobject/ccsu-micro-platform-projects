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
public interface Barista {

    String OUTPUT_CHANNEL = "output_channel";

    String INPUT_CHANNEL = "notify_channel";

    @Output(Barista.OUTPUT_CHANNEL)
    MessageChannel output();

    @Input(Barista.INPUT_CHANNEL)
    SubscribableChannel input();

}
