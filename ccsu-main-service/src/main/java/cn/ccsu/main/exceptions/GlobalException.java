package cn.ccsu.main.exceptions;

import cn.ccsu.common.enums.ResultEnum;
import lombok.Getter;

/**
 * @author hangs.zhang
 * @date 2018/11/20
 * *****************
 * function:
 */
@Getter
public class GlobalException extends RuntimeException {

    private Integer code;

    public GlobalException(ResultEnum resultEnum) {
        this(resultEnum.getCode(), resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public GlobalException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public GlobalException() {
        super();
    }

}
