/*
 * Created by Long Duping
 * Date 2018/12/5 15:40
 */
package cn.ccsu.user.api;

import cn.ccsu.common.cnt.Const;
import cn.ccsu.user.deps.SessionService;
import cn.ccsu.user.service.UserService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    @Autowired
    private SessionService sessionService;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String param) {
        String platform = request.getHeader("platform");
        String res;
        // 判断平台类型
        if (Const.PlatformType.MINI_PROGRAM.equals(platform)) {
            res = miniProgramUserService.login(param);
        } else {
            res = appUserService.login(param);
        }
        return res;
    }

    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    public String getUserInfo(String sessionId) {
        JSONObject returnJson = new JSONObject();

        JSONObject sessionJson = JSONObject.parseObject(sessionService.getSessionInfo(sessionId));
        int errcode = sessionJson.getIntValue("errcode");
        if (0 != errcode) {
            returnJson.put("errcode", errcode);
            returnJson.put("errmsg", sessionJson.getString("errmsg"));
            return returnJson.toString();
        }
        JSONObject sessionInfo = JSONObject.parseObject(sessionJson.getString("sessionInfo"));
        // TODO 这里为测试数据，实际放入用户信息
        returnJson.put("nickName", sessionInfo.get("openId"));
        returnJson.put("jwcAccount", sessionInfo.get("sessionKey"));
        returnJson.put("errcode", 0);
        returnJson.put("errmsg", "success");
        return returnJson.toString();
    }
}
