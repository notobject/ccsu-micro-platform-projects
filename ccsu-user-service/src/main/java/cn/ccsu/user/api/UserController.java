/*
 * Created by Long Duping
 * Date 2018/12/5 15:40
 */
package cn.ccsu.user.api;

import cn.ccsu.common.cnt.Const;
import cn.ccsu.user.service.SessionService;
import cn.ccsu.user.service.UserService;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RefreshScope
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Resource(name = "miniProgramUserService")
    private UserService miniProgramUserService;
    @Resource(name = "appUserService")
    private UserService appUserService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private SessionService sessionService;

    /**
     * TODO 学号绑定
     *
     * @return
     */
    @PostMapping("/bind")
    public String bind() {
        return "暂未实现";
    }

    @PostMapping("/login")
    public String login(@RequestParam String code , @RequestParam String rawData) {
        log.info("appPlatform: {}", request.getHeader("appPlatform"));
        if (StringUtils.isEmpty(code)) {
            JSONObject returnJson = new JSONObject();
            returnJson.put("errcode", 10000);
            returnJson.put("errmsg", "code is empty");
            log.error(returnJson.toString());
            return returnJson.toString();
        }
        log.info("code: {}", code);
        return miniProgramUserService.login(code,rawData);
    }

    @GetMapping("/getUserInfo")
    public String getUserInfo(@RequestParam String sessionId) throws IOException {
//        log.info("sessionId: " + request.getHeader("sessionId"));
//        log.info("appPlatform: " + request.getHeader("appPlatform"));
//        log.info("appVersion: " + request.getHeader("appVersion"));
//        String body = StreamUtils.copyToString(request.getInputStream(), Charset.forName("UTF-8"));
//        JSONObject jsonObject = JSONObject.parseObject(body);
//        if (jsonObject != null)
//            log.info("body: " + jsonObject.toString());

        JSONObject returnJson = new JSONObject();
        JSONObject sessionJson = sessionService.getSessionInfo(sessionId);
        int errcode = sessionJson.getIntValue("errcode");
        if (0 != errcode) {
            returnJson.put("errcode", errcode);
            returnJson.put("errmsg", sessionJson.getString("errmsg"));
            return returnJson.toString();
        }
        return sessionJson.toString();
    }
}
