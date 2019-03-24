/*
 * Created by Long Duping
 * Date 2019-02-15 14:42
 */
package cn.ccsu.user.entity;

import lombok.Data;

import java.util.Date;

@Data
public class UserInfo {
    private Long id;
    private String openId;
    private String nickName;
    private String avatarUrl;
    private int gender;
    private String city;
    private String province;
    private String country;
    private Integer roleId;
    private String stuNumber;
    private String realName;
    private Date createTime;
    private Date lastLoginTime;
}
