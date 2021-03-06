/*
 * Created by Long Duping
 * Date 2019-03-31 15:15
 */
package cn.ccsu.controlcenter.pojo;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@ToString
@Table(name = "t_service_info")
public class ServiceInfo {
    //服务ID
    @Id
    private Integer id;
    //服务名称
    private String serviceName;
    //服务类型
    private String serviceType;
    //仓库地址
    private String repoUrl;
    //Version
    private String versionName;
    //服务端口
    private Integer servicePort;
    //构建命令
    private String buildCmd;
    //创建时间
    private Date createTime;
    //负责人（支持多个）
    private String creator;
    //主要创建者ID（仅一个）
    private Integer creatorId;
    //备注
    private String note;

}
