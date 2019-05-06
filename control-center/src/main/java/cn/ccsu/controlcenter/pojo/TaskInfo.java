/*
 * Created by Long Duping
 * Date 2019-03-31 15:53
 */
package cn.ccsu.controlcenter.pojo;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Data
@Table(name = "t_task_info")
@ToString
public class TaskInfo {

    @Id
    private String id;
    private String action;
    private Date createTime;
    private Integer creatorId;
    private String currentStatus;
    private MachineInfo machine;
    private List<String> messages;
    private int mark;
}
