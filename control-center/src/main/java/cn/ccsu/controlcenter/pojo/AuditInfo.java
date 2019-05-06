/*
 * Created by Long Duping
 * Date 2019-05-05 11:09
 */
package cn.ccsu.controlcenter.pojo;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import javax.persistence.Table;

@ToString
@Data
@Table(name = "t_audit_info")
public class AuditInfo {

    @Id
    private Integer id;
    private Integer userId;
    private String opType;
    private String opDetail;
    private String opTime;
    private String opStatus;
}
