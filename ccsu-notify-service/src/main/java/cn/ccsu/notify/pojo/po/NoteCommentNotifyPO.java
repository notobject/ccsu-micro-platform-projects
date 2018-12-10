package cn.ccsu.notify.pojo.po;

import cn.ccsu.notify.enums.NotifyStatus;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author hangs.zhang
 * @date 2018/12/10
 * *****************
 * function:
 * 帖子评论持久层对象
 */
@Data
public class NoteCommentNotifyPO {

    // 通知id
    private Integer notifyId;

    // 帖子id
    private Integer noteId;

    // 发出通知的人
    private String authorId;

    // 发送通知的时间
    private Date sendTime;

    // 帖子通知内容
    private String message;

    private Integer status;

}
