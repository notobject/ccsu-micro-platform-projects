package cn.ccsu.notify.stream.message;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author hangs.zhang
 * @date 2018/12/11
 * *****************
 * function:
 */
@Data
public class NotifyMessage {

    // 存放如果用户在线，则需要通知的用户唯一id
    private List<Integer> userIds;

    private Notify notify;

    // 通知基类
    @Data
    public static class Notify {

        private Date sendTime;

        private String message;

        private String notifyType;

        private NoteCommentNotify noteCommentNotify;

        private SystemNotify systemNotify;

    }

    // 文章评论通知
    @Data
    public static class NoteCommentNotify {

        private Integer noteId;

        private Integer authorId;

    }

    // 活动招聘通知
    @Data
    public static class SystemNotify {

        private String activityName;

        private String systemNotifyType;

    }

}
