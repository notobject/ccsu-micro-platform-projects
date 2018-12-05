/*
 * Created by Long Duping
 * Date 2018/12/5 19:04
 */
package cn.ccsu.common.base;

import java.io.Serializable;

public class BaseEntity implements Serializable {
    private Integer errcode;
    private String errmsg;

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
