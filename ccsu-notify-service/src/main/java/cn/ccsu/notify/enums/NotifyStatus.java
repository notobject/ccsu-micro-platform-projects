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
    unread("未读"),
    read("已读"),
    delete("已删除")
    ;

    private String status;

    NotifyStatus(String status) {
        this.status = status;
    }
}
