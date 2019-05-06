/*
 * Created by Long Duping
 * Date 2019-03-31 15:14
 */
package cn.ccsu.controlcenter.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseRes implements Serializable {
    private int errcode;
    private String errmsg;
    private Object data;
}
