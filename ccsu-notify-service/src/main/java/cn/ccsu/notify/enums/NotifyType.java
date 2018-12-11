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
    // 活动
    activity(0, "activity"),
    // 招聘
    recruit(1, "recruit"),
    // 评论
    comment(2, "comment");

    private Integer code;

    private String name;

    NotifyType(Integer code, String type) {
        this.code = code;
        this.name = type;
    }

    public static String getNameByCode(int code) {
        for (NotifyType notifyType : NotifyType.values()) {
            if (notifyType.code.equals(code)) {
                return notifyType.name;
            }
        }
        return "not exist";
    }

    public static NotifyType getNotifyTypeByName(String name) {
        for(NotifyType notifyType : NotifyType.values()) {
            if(notifyType.name.equals(name)) {
                return notifyType;
            }
        }
        return null;
    }

}
