/*
 * Created by Long Duping
 * Date 2019-02-15 16:16
 */
package cn.ccsu.user.service;

import cn.ccsu.user.util.TokenUtils;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class SessionService {
    private static final Logger log = LoggerFactory.getLogger(SessionService.class);

    @Value("${expireIn: 7200}")
    private int expiredIn;           // 会话过期时间（默认 7200 秒）

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    public JSONObject newSession(String data) {
        JSONObject returnJson = new JSONObject();
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String sessionId = TokenUtils.getToken();
        log.info("redis set {} = {}", sessionId, data);
        ops.set(sessionId, data == null ? "null" : data, expiredIn, TimeUnit.SECONDS);
        returnJson.put("errcode", 0);
        returnJson.put("errmsg", "success");
        returnJson.put("sessionId", sessionId);
        returnJson.put("expired_in", expiredIn);
        return returnJson;
    }

    public JSONObject getSessionInfo(String sessionId) {
        JSONObject returnJson = new JSONObject();
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String sessionInfo = ops.get(sessionId);
        log.info("redis get {} = ", sessionId, sessionInfo);
        if (sessionInfo == null) {
            // 两种情况：sessionId 不存在，sessionId 已过期。这里统一当作过期处理
            returnJson.put("errcode", 10012);
            returnJson.put("errmsg", "your sessionId was not exist or expired.");
            return returnJson;
        }
        redisTemplate.expire(sessionId, expiredIn, TimeUnit.SECONDS);
        returnJson.put("errcode", 0);
        returnJson.put("errmsg", "success");
        returnJson.put("userInfo", sessionInfo);
        return returnJson;
    }
}