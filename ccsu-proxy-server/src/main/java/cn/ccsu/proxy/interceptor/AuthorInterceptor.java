/*
 * Created by Long Duping
 * Date 2018/12/6 13:35
 */
package cn.ccsu.proxy.interceptor;


import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 访问权限校验
 */
public class AuthorInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //TODO
        return true;
    }
}
