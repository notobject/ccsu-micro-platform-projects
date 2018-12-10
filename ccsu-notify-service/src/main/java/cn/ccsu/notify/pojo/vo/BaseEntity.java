package cn.ccsu.notify.pojo.vo;

import lombok.Data;

/**
 * @author hangs.zhang
 * @date 2018/12/10
 * *****************
 * function:
 */
@Data
public class BaseEntity<T> {

    // 错误代码
    private Integer errcode;

    // 错误信息
    private String errmsg;

    private T data;

}
