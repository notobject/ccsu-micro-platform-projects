/*
 * Created by Long Duping
 * Date 2018/12/5 16:40
 */
package cn.ccsu.user.deps;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@FeignClient("ccsu-session-service")
public interface SessionService {

    @PostMapping("newSession")
    String newSession(@RequestParam("data") String data);

    @GetMapping("getSessionInfo")
    String getSessionInfo(@RequestParam("sessionId") String sessionId);
}
