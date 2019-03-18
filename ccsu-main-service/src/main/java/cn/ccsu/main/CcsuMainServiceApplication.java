package cn.ccsu.main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@EnableDiscoveryClient
@RestController
@MapperScan(basePackages = "cn.ccsu.main.dao")
public class CcsuMainServiceApplication {

    @Value("${tag: unkown}")
    private String tag;

    public static void main(String[] args) {
        SpringApplication.run(CcsuMainServiceApplication.class, args);
    }

    @RequestMapping("/tag")
    public String version() {
        return "tag: " + tag;
    }

}

