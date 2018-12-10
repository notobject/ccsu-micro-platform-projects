package cn.ccsu.notify.pojo.po;

import cn.ccsu.notify.enums.NotifyStatus;
import lombok.Data;

import java.util.Date;

/**
 * @author hangs.zhang
 * @date 2018/12/10
 * *****************
 * function:
 * 帖子评论持久层对象
 */
@Data
public class NoteCommentPO {

    // 通知id
    private Integer id;

    // 帖子id
    private Integer noteId;

    // 发出通知的人
    private String author;

    // 通知的状态
    private NotifyStatus status;

    // 通知的时间
    private Date date;

    // 帖子通知内容
    private String message;

}
