/*
 * Created by Long Duping
 * Date 2018/12/6 13:37
 */
package cn.ccsu.proxy.conf;

import cn.ccsu.proxy.interceptor.AuthorInterceptor;
import cn.ccsu.proxy.interceptor.HeaderInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 拦截器配置
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HeaderInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/tag");
        registry.addInterceptor(new AuthorInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login")
                .excludePathPatterns("/tag");
        super.addInterceptors(registry);
    }
}
