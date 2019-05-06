package cn.ccsu.notify.enums;

import lombok.Getter;

/**
 * @author hangs.zhang
 * @date 2018/12/10
 * *****************
 * function:
 * web 返回码
 */
@Getter
public enum  ResultEnum {
    SUCCESS(0, "success");

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
