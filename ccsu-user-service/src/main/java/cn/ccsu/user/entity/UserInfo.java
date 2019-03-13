/*
 * Created by Long Duping
 * Date 2019-02-15 14:42
 */
package cn.ccsu.user.entity;

public class UserInfo {
    private int id;
    private String openId;
    private String avatar;
    private String nickName;
    private String realName;
    private String stuNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getStuNumber() {
        return stuNumber;
    }

    public void setStuNumber(String stuNumber) {
        this.stuNumber = stuNumber;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", openId='" + openId + '\'' +
                ", avatar='" + avatar + '\'' +
                ", nickName='" + nickName + '\'' +
                ", realName='" + realName + '\'' +
                ", stuNumber='" + stuNumber + '\'' +
                '}';
    }
}
