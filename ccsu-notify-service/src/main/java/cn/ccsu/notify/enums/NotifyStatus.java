package cn.ccsu.notify.enums;

import lombok.Getter;

/**
 * @author hangs.zhang
 * @date 2018/12/10
 * *****************
 * function:
 * 站内通知-状态
 */
@Getter
public enum NotifyStatus {
    unread(0, "未读"),
    read(1, "已读"),
    delete(2, "已删除");

    private Integer code;

    private String status;

    NotifyStatus(Integer code, String status) {
        this.code = code;
        this.status = status;
    }
}
