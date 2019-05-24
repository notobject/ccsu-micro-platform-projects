package cn.ccsu.controlcenter;

import cn.ccsu.controlcenter.dao.AuditDAO;
import cn.ccsu.controlcenter.pojo.AuditInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import tk.mybatis.spring.annotation.MapperScan;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 服务治理平台
 */
@Controller
@SpringBootApplication(scanBasePackages = "cn.ccsu.controlcenter")
@MapperScan(basePackages = "cn.ccsu.controlcenter.dao")
public class ControlCenterApplication {

    @Autowired
    private AuditDAO auditDAO;

    public static void main(String[] args) {
        SpringApplication.run(ControlCenterApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @GetMapping("/")
    public ModelAndView index(ModelAndView modelAndView, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return new ModelAndView("redirect:/user/login");
        }
        List<AuditInfo> auditInfos = auditDAO.selectAll();
        auditInfos.sort((o1, o2) -> o2.getId() - o1.getId());
        if (auditInfos != null && auditInfos.size() > 20)
            modelAndView.addObject("audits", auditInfos.subList(0, 20));
        else
            modelAndView.addObject("audits", auditInfos);
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping("/error")
    public String error(String msg, Model model) {
        model.addAttribute("msg", msg);
        return "views/error";
    }
}
