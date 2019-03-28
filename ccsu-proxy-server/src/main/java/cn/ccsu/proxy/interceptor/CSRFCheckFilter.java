/*
 * Created by Long Duping
 * Date 2019-03-28 14:42
 */
package cn.ccsu.proxy.interceptor;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import javax.servlet.http.HttpServletRequest;

/**
 * CSRF (跨站请求伪造) 防御。
 * 1. 校验 Referer 是否合法
 * 2. 否则校验是否携带token，以及token的有效性。
 * 3. 以上两种方式基本能够有效防御。
 */
public class CSRFCheckFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return RequestContext.getCurrentContext().sendZuulResponse();
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        // Referer 校验
        String referer = request.getHeader("Referer");

        // Token校验
        String token = request.getParameter("token");


        return null;
    }
}
