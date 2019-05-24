/*
 * Created by Long Duping
 * Date 2019-05-05 11:02
 */
package cn.ccsu.controlcenter.pojo;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Data
@ToString
public class MachineInfo {

    @Id
    private String id;
    private String alias;
    private String ip;
    private String queue;
    private String user;
    private String passwd;
    private Date lastHbTime;
    private String status;
    private int cpuNum;
    private long totalMem;
    private long totalDisk;
    private float freeCpu;
    private long freeMem;
    private long freeDisk;
    private List<String> machineType;

}
