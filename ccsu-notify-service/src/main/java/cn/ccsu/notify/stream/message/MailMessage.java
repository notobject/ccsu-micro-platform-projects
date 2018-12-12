package cn.ccsu.notify.stream.message;

import lombok.Data;

/**
 * @author hangs.zhang
 * @date 2018/12/12
 * *****************
 * function:
 */
@Data
public class MailMessage {

    private String subject;

    private String destination;

    private String text;

}
