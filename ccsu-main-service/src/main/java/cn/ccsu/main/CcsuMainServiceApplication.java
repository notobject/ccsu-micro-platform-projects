package cn.ccsu.main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "cn.ccsu.main.dao")
public class CcsuMainServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(CcsuMainServiceApplication.class, args);
    }

}

