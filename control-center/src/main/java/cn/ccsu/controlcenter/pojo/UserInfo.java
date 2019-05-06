/*
 * Created by Long Duping
 * Date 2019-03-31 15:46
 */
package cn.ccsu.controlcenter.pojo;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@ToString
@Table(name = "t_user_info")
public class UserInfo {

    @Id
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String name;
    private String phone;
    private String role;
    private Date createTime;
    private int status;
}
