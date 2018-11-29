package cn.ccsu.register;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RegisterServerApplication {
    public static void main(String[] args) {

        SpringApplication.run(RegisterServerApplication.class, args);
    }
}
