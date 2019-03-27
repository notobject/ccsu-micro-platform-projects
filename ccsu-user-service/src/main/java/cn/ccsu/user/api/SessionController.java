/*
 * Created by Long Duping
 * Date 2018/12/5 12:35
 */
package cn.ccsu.user.api;

import cn.ccsu.user.service.SessionService;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RefreshScope
public class SessionController {
    private static final Logger log = LoggerFactory.getLogger(SessionController.class);
    @Autowired
    private SessionService sessionService;
    @Autowired
    private HttpServletRequest request;

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
    public String getSessionInfo(@RequestBody String json, String sessionId) {
        log.info("sessionId: " + request.getHeader("sessionId"));
        log.info("appPlatform: " + request.getHeader("appPlatform"));
        log.info("appVersion: " + request.getHeader("appVersion"));
        log.info("body", json);
        if (Strings.isEmpty(sessionId)) {
            sessionId = request.getHeader("sessionId");
        }
        return sessionService.getSessionInfo(sessionId).toJSONString();
    }
}
