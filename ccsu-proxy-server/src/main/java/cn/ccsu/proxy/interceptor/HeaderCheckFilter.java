/*
 * Created by Long Duping
 * Date 2019-02-16 21:01
 */
package cn.ccsu.proxy.interceptor;

import cn.ccsu.common.util.HeaderParser;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.http.util.TextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class HeaderCheckFilter extends ZuulFilter {
    public static final Logger log = LoggerFactory.getLogger(HeaderCheckFilter.class);

    @Value("${requiredHeaders: appPlatform,appVersion,sessionId}")
    private String requiredHeaders = "appPlatform,appVersion,sessionId";

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return RequestContext.getCurrentContext().sendZuulResponse();
    }

    @Override
    public Object run() throws ZuulException {
        if (RequestContext.getCurrentContext().getRequest().getRequestURI().equals("/ccsu-user-service/login"))
            return null;
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        Map<String, String> headers = null;
        try {
            headers = HeaderParser.parse(request);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (null == headers || headers.size() == 0) {
            log.info("Request Intercepted: {}, Case: headers is empty", request.getRequestURI());
            RespUtil.resp(10000, "headers is empty");
            return false;
        }
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            log.info("key={},value={}", entry.getKey(), entry.getValue());
        }
        String[] rhs = requiredHeaders.split(",");
        log.info("requiredHeaders: {}, conut: {}", requiredHeaders, rhs.length);

        for (String h : rhs) {
            if (TextUtils.isEmpty(headers.get(h))) {
                // 只要有一个必填项为空，则拦截请求
                log.info("Request Intercepted: {}, Case: param {} is empty", request.getRequestURI(), h);
                RespUtil.resp(10000, "sessionId is empty");
                return false;
            }
        }
        return null;
    }
}
