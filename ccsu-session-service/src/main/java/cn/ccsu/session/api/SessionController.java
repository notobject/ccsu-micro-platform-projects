/*
 * Created by Long Duping
 * Date 2018/12/5 12:35
 */
package cn.ccsu.session.api;

import cn.ccsu.session.util.TokenUtils;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RefreshScope
public class SessionController {

    @Value("${expireIn: 7200}")
    private int expiredIn;           // 会话过期时间（默认 7200 秒）

    @Autowired
    StringRedisTemplate template;

    /**
     * 开启一个新的会话
     *
     * @param data
     * @return 新的会话ID
     */
    @RequestMapping("/newSession")
    public String newSession(@RequestParam String data) {
        JSONObject returnJson = new JSONObject();

        ValueOperations<String, String> ops = template.opsForValue();
        String sessionId = TokenUtils.getToken();
        ops.set(sessionId, data == null ? "null" : data, expiredIn, TimeUnit.SECONDS);

        returnJson.put("errcode", 0);
        returnJson.put("errmsg", "success");
        returnJson.put("sessionId", sessionId);
        returnJson.put("expired_in", expiredIn);
        return returnJson.toJSONString();
    }

    /**
     * 根据会话ID获取会话信息
     *
     * @param sessionId
     * @return 会话信息
     */
    @RequestMapping(value = "/getSessionInfo", method = RequestMethod.GET)
    public String getSessionInfo(@RequestParam String sessionId) {
        JSONObject returnJson = new JSONObject();

        ValueOperations<String, String> ops = template.opsForValue();
        String sessionInfo = ops.get(sessionId);
        if (sessionInfo == null) {
            // 两种情况：sessionId 不存在，sessionId 已过期。这里统一当作过期处理
            returnJson.put("errcode", 10012);
            returnJson.put("errmsg", "sessionId was expired.");
            return returnJson.toJSONString();
        }
        returnJson.put("errcode", 0);
        returnJson.put("errmsg", "success");
        returnJson.put("sessionInfo", sessionInfo);
        return returnJson.toJSONString();
    }
}
