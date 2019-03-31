package cn.ccsu.team.config;

import cn.ccsu.team.CcsuTeamServiceApplication;
import cn.ccsu.team.handler.OpenIdResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author hangs.zhang
 * @date 2019/3/30
 * *****************
 * function:
 */
@Configuration
@ComponentScan(basePackageClasses = CcsuTeamServiceApplication.class, useDefaultFilters = true)
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private OpenIdResolver openIdResolver;

    /**
     * 配置web静态资源位置
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(openIdResolver);
    }

}
