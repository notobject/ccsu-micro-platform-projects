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
    activity(0, "活动"),
    advertiseOffer(1, "招聘"),
    comment(2, "评论通知");

    private Integer code;

    private String type;

    NotifyType(Integer code, String type) {
        this.code = code;
        this.type = type;
    }
}
