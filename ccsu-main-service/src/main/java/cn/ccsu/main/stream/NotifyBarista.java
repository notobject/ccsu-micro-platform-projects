package cn.ccsu.main.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author hangs.zhang
 * @date 2018/12/9
 * *****************
 * function:
 */
public interface NotifyBarista {

    String OUTPUT_CHANNEL = "notify_output_channel";

    @Output(NotifyBarista.OUTPUT_CHANNEL)
    MessageChannel output();

}
