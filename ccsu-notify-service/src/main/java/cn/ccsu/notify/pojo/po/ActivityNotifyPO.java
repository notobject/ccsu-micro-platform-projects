package cn.ccsu.notify.pojo.po;

import lombok.Data;

import java.util.Date;

/**
 * @author hangs.zhang
 * @date 2018/12/10
 * *****************
 * function:
 */
@Data
public class ActivityNotifyPO {

    private Integer notifyId;

    private Integer type;

    private String message;

    private Date sendTime;

    private String activityName;

    private Integer status;

}
