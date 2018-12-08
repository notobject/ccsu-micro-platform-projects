/*
 * Created by Long Duping
 * Date 2018/12/6 13:35
 */
package cn.ccsu.proxy.interceptor;


import org.apache.http.util.TextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 访问权限校验
 */
public class AuthorInterceptor extends HandlerInterceptorAdapter {
    public static final Logger log = LoggerFactory.getLogger(AuthorInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String sessionId = request.getHeader("sessionId");
        if (TextUtils.isEmpty(sessionId) || TextUtils.isEmpty(request.getParameter("sessionId"))) {
            log.info("请求拦截：" + request.getRequestURI());
            return false;
        }
        return true;
    }
}
