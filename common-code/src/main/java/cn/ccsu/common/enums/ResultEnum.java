package cn.ccsu.common.enums;

import lombok.Getter;

/**
 * @author hangs.zhang
 * @date 2018/12/10
 * *****************
 * function:
 * api 返回码
 */
@Getter
public enum ResultEnum {
    SUCCESS(0, "success"),
    PARAM_ERROR(-10005, "param error"),
    SERVER_INNER_ERROR(-10006, "server inner error"),
    ;

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
