package cn.ccsu.common.enums;

/**
 * @author hangs.zhang
 * @date 2018/12/12
 * *****************
 * function: 系统通知类型
 */
public enum SystemNotifyType {
    // 活动类型通知
    activity,
    // 招聘类型通知
    recruit;

    public static SystemNotifyType getSystemNotifyType(String name) {
        return SystemNotifyType.valueOf(name);
    }

}
