package cn.ccsu.notify.pojo.dto;

import lombok.Data;

/**
 * @author hangs.zhang
 * @date 2018/12/10
 * *****************
 * function:
 */
@Data
public class ActivityNotifyDTO extends Notify {

    private String type;

    private String activityName;

}
