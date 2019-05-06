/*
 * Created by Long Duping
 * Date 2019-05-06 20:39
 */
package cn.ccsu.controlcenter.pojo;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class AckInfo {
    private Integer code;
    private String msg;
    private Integer serialNo;
    private String cmd;
    private Long timestamp;
    private String taskId;
}
