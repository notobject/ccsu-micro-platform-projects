package cn.ccsu.config;

import cn.ccsu.config.filter.BusRefreshFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.servlet.Filter;

/**
 * 动态刷新配置
 * curl -X POST http://serverb1.notobject.com:8888/actuator/bus-refresh
 */
@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
@ComponentScan(basePackages = "cn.ccsu.config")
public class ConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean registeFilters() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(busRefreshFilter());
        registration.addUrlPatterns("/actuator/bus-refresh");
        registration.setName("busRefreshFilter");
        return registration;
    }

    @Bean("busRefreshFilter")
    public Filter busRefreshFilter() {
        return new BusRefreshFilter();
    }
}
