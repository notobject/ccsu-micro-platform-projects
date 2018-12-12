package cn.ccsu.notify.pojo.dto;

import lombok.Data;

/**
 * @author hangs.zhang
 * @date 2018/12/10
 * *****************
 * function:
 */
@Data
public class SystemNotifyDTO extends Notify {

    private String systemNotifyType;

    private String activityName;

}
