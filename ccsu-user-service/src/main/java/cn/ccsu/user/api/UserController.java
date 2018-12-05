package cn.ccsu.user.api;


import cn.ccsu.common.cnt.Const;
import cn.ccsu.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RefreshScope
public class UserController {

    @Resource(name = "miniProgramUserService")
    private UserService miniProgramUserService;

    @Resource(name = "appUserService")
    private UserService appUserService;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String param) {
        String platform = request.getHeader("platform");
        // 判断平台类型
        if (Const.PlatformType.MINI_PROGRAM.equals(platform)) {
            miniProgramUserService.login(param);
        } else {
            appUserService.login(param);
        }
        return "";
    }
}
