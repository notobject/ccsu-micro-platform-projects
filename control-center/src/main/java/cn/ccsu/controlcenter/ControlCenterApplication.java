package cn.ccsu.controlcenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 服务治理平台
 */
@Controller
@SpringBootApplication(scanBasePackages = "cn.ccsu.controlcenter")
@MapperScan(basePackages = "cn.ccsu.controlcenter.dao")
public class ControlCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(ControlCenterApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/error")
    public String error(String msg, Model model) {
        model.addAttribute("msg", msg);
        return "views/error";
    }
}
