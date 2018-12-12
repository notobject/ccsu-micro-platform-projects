package cn.ccsu.notify.enums;

import lombok.Getter;

/**
 * @author hangs.zhang
 * @date 2018/12/10
 * *****************
 * function:
 */
@Getter
public enum NotifyType {
    // 系统类型通知
    system,
    // 评论类型通知
    comment;

    public static NotifyType getNotifyType(String name) {
        return NotifyType.valueOf(name);
    }

}
