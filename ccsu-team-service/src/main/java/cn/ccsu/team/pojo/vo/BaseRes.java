package cn.ccsu.team.pojo.vo;

import lombok.Data;

/**
 * @author hangs.zhang
 * @date 2019/1/25
 * *****************
 * function:
 */
@Data
public class BaseRes<T> {

    // 错误代码
    private Integer errcode;

    // 错误信息
    private String errmsg;

    private T data;

}
