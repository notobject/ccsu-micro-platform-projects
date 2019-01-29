package cn.ccsu.main.utils;

import cn.ccsu.common.enums.ResultEnum;
import cn.ccsu.main.pojo.vo.BaseRes;

/**
 * @author hangs.zhang
 * @date 2018/12/10
 * *****************
 * function:
 */
public class BaseResUtil {

    public static BaseRes success(Object obj) {
        BaseRes baseRes = new BaseRes();
        baseRes.setErrcode(ResultEnum.SUCCESS.getCode());
        baseRes.setErrmsg(ResultEnum.SUCCESS.getMsg());
        baseRes.setData(obj);
        return baseRes;
    }

    public static BaseRes success() {
        return success(null);
    }

    public static BaseRes error(Integer code, String msg) {
        BaseRes baseRes = new BaseRes();
        baseRes.setErrcode(code);
        baseRes.setErrmsg(msg);
        baseRes.setData(null);
        return baseRes;
    }

    public static BaseRes error(ResultEnum resultEnum) {
        BaseRes baseRes = new BaseRes();
        baseRes.setErrcode(resultEnum.getCode());
        baseRes.setErrmsg(resultEnum.getMsg());
        baseRes.setData(null);
        return baseRes;
    }

}
