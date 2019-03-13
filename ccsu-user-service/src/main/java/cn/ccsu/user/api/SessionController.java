/*
 * Created by Long Duping
 * Date 2018/12/5 12:35
 */
package cn.ccsu.user.api;

import cn.ccsu.user.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class SessionController {

    @Autowired
    private SessionService sessionService;

    /**
     * 开启一个新的会话
     *
     * @param data
     * @return 新的会话ID
     */
    @RequestMapping("/newSession")
    public String newSession(@RequestParam String data) {
        return sessionService.newSession(data).toJSONString();
    }

    /**
     * 根据会话ID获取会话信息
     *
     * @param sessionId
     * @return 会话信息
     */
    @RequestMapping(value = "/getSessionInfo", method = RequestMethod.GET)
    public String getSessionInfo(@RequestParam String sessionId) {
        return sessionService.getSessionInfo(sessionId).toJSONString();
    }
}
