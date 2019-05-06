/*
 * Created by Long Duping
 * Date 2019-03-31 15:58
 */
package cn.ccsu.controlcenter.util;

import cn.ccsu.controlcenter.pojo.BaseRes;

public class Resp {


    public static BaseRes success(Object obj) {
        BaseRes baseRes = new BaseRes();
        baseRes.setErrcode(0);
        baseRes.setErrmsg("success");
        baseRes.setData(obj);
        return baseRes;
    }

    public static BaseRes failed(int errcode, String errmsg) {
        BaseRes baseRes = new BaseRes();
        baseRes.setErrcode(errcode);
        baseRes.setErrmsg(errmsg);
        return baseRes;
    }
}
