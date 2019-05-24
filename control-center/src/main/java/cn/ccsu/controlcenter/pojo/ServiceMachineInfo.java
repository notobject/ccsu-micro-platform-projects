/*
 * Created by Long Duping
 * Date 2019-05-09 13:43
 */
package cn.ccsu.controlcenter.pojo;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@ToString
@Table(name = "t_service_machine_info")
public class ServiceMachineInfo {

    @Id
    private Integer id;

    private Integer sid;

    private String mid;

    private Integer port;

    private String creator;

    private String status;

    private Date createTime;
}
