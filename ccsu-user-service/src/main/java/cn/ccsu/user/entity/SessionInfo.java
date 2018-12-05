/*
 * Created by Long Duping
 * Date 2018/12/5 19:45
 */
package cn.ccsu.user.entity;

import cn.ccsu.common.base.BaseEntity;

public class SessionInfo extends BaseEntity {
    private Integer expired_in;
    private String sessionId;

    public Integer getExpired_in() {
        return expired_in;
    }

    public void setExpired_in(Integer expired_in) {
        this.expired_in = expired_in;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
