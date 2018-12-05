/*
 * Created by Long Duping
 * Date 2018/12/5 16:40
 */
package cn.ccsu.user.deps;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient("ccsu-session-service")
public interface SessionService {

    @RequestMapping(value = "/newSession", method = RequestMethod.POST)
    String newSession(@RequestParam("data") String data);

    @RequestMapping(value = "/getSessionInfo", method = RequestMethod.GET)
    String getSessionInfo(@RequestParam("sessionId") String sessionId);
}
