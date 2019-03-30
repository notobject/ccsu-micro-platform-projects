package cn.ccsu.team;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@MapperScan(basePackages = "cn.ccsu.team.dao")
public class CcsuTeamServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CcsuTeamServiceApplication.class, args);
    }

}

