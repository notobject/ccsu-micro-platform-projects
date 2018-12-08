/*
 * Created by Long Duping
 * Date 2018/12/6 13:40
 */
package cn.ccsu.proxy.interceptor;

import cn.ccsu.common.entity.RequestHeader;
import cn.ccsu.common.util.HeaderParser;
import org.apache.http.util.TextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 请求头必填参数校验
 */
public class HeaderInterceptor extends HandlerInterceptorAdapter {
    public static final Logger log = LoggerFactory.getLogger(AuthorInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        RequestHeader header = HeaderParser.parse(request);
        assert header != null;

        boolean flag;
        flag = TextUtils.isEmpty(header.getAppPlatform());
        flag = flag ||  TextUtils.isEmpty(header.getAppVersion());

        if (flag){
            // 只要有一个必填项为空，则返回false  拦截请求
            log.info("请求拦截：" + request.getRequestURI());
            return false;
        }
        return true;
    }
}
