package cn.ccsu.proxy;

import cn.ccsu.proxy.conf.InterceptorConfig;
import cn.ccsu.proxy.interceptor.AccessCheckFilter;
import cn.ccsu.proxy.interceptor.HeaderCheckFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
@RestController
@RefreshScope
@Import(InterceptorConfig.class)
public class ProxyServerApplication {
    @Value("${tag: unkown}")
    private String tag;

    public static void main(String[] args) {

        SpringApplication.run(ProxyServerApplication.class, args);
    }

    @RequestMapping("/tag")
    public String version() {
        return "tag: " + tag;
    }

    @Bean
    AccessCheckFilter accessCheckFilter() {
        return new AccessCheckFilter();
    }

    @Bean
    HeaderCheckFilter headerCheckFilter() {
        return new HeaderCheckFilter();
    }

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
