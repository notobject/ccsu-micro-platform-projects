/*
 * Created by Long Duping
 * Date 2019-02-16 21:03
 */
package cn.ccsu.proxy.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.context.RequestContext;

public class RespUtil {

    public static void resp(int errcode, String errmsg) {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.setSendZuulResponse(false);
        ctx.setResponseStatusCode(200);
        JSONObject ret = new JSONObject();
        ret.put("errcode", errcode);
        ret.put("errmsg", errmsg);
        ctx.setResponseBody(ret.toString());
    }
}
