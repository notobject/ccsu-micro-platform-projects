package cn.ccsu.notify.utils;

import cn.ccsu.notify.enums.ResultEnum;
import cn.ccsu.notify.pojo.vo.BaseEntity;

/**
 * @author hangs.zhang
 * @date 2018/12/10
 * *****************
 * function:
 */
public class BaseEntityUtil {

    public static BaseEntity success(Object obj) {
        BaseEntity baseRes = new BaseEntity();
        baseRes.setErrcode(ResultEnum.SUCCESS.getCode());
        baseRes.setErrmsg(ResultEnum.SUCCESS.getMsg());
        baseRes.setData(obj);
        return baseRes;
    }

    public static BaseEntity success() {
        return success(null);
    }

    public static BaseEntity error(Integer code, String msg) {
        BaseEntity baseRes = new BaseEntity();
        baseRes.setErrcode(code);
        baseRes.setErrmsg(msg);
        baseRes.setData(null);
        return baseRes;
    }

    public static BaseEntity error(ResultEnum resultEnum) {
        BaseEntity baseRes = new BaseEntity();
        baseRes.setErrcode(resultEnum.getCode());
        baseRes.setErrmsg(resultEnum.getMsg());
        baseRes.setData(null);
        return baseRes;
    }

}
