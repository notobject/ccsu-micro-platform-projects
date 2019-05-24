/*
 * Created by Long Duping
 * Date 2019-05-05 11:09
 */
package cn.ccsu.controlcenter.pojo;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import javax.persistence.Table;
import java.util.Date;

@ToString
@Data
@Table(name = "t_audit_info")
public class AuditInfo {

    @Id
    private Integer id;
    private String user;
    private String opType;
    private String opDetail;
    private Date opTime;
    private String opStatus;

    public AuditInfo(String user, String opType, String opDetail, boolean opStatus) {
        this.user = user;
        this.opType = opType;
        this.opDetail = opDetail;
        this.opStatus = opStatus ? "success" : "failed";
        this.opTime = new Date(System.currentTimeMillis());
    }
}
