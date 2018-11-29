package cn.ccsu.config.filter;

import cn.ccsu.config.wapper.CustometRequestWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 在使用 github 的webhooks自动刷新配置时，会携带一些请求参数过来，导致json转换异常。
 * 因此使用过滤器拦截该地址将请求体置空
 */
@WebFilter("/actuator/bus-refresh")
public class BusRefreshFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println(" Refresh Config");
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String url = new String(httpServletRequest.getRequestURI());
        if (!url.endsWith("/bus-refresh")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        CustometRequestWrapper requestWrapper = new CustometRequestWrapper(httpServletRequest);
        filterChain.doFilter(requestWrapper, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
