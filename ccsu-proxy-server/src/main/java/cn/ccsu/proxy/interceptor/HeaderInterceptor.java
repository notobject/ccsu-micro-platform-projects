/*
 * Created by Long Duping
 * Date 2018/12/6 13:40
 */
package cn.ccsu.proxy.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 请求头必填参数校验
 */
public class HeaderInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //TODO

        return true;
    }
}
