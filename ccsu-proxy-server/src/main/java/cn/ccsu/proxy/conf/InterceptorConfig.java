/*
 * Created by Long Duping
 * Date 2018/12/6 13:37
 */
package cn.ccsu.proxy.conf;

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
        /**
         * 换 Zuul 的 filter实现了
         */
        super.addInterceptors(registry);
    }
}
