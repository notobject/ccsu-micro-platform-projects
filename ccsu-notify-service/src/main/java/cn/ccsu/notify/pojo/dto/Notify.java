package cn.ccsu.notify.pojo.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author hangs.zhang
 * @date 2018/12/10
 * *****************
 * function:
 */
@Data
public class Notify {

    // 标志位 区分 system,noteComment
    private String notifyType;

    private Integer notifyId;

    private String message;

    private Date sendTime;

    // 0 未读 1 已读 2 删除
    private Integer status;

}
