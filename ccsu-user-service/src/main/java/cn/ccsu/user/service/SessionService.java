/*
 * Created by Long Duping
 * Date 2019-02-15 16:16
 */
package cn.ccsu.user.service;

import cn.ccsu.user.util.TokenUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.TimeUnit;

@Service
public class SessionService {

    @Value("${expireIn: 7200}")
    private int expiredIn;           // 会话过期时间（默认 7200 秒）

    @Autowired
    StringRedisTemplate template;

    public JSONObject newSession(String data) {
        JSONObject returnJson = new JSONObject();
        ValueOperations<String, String> ops = template.opsForValue();
        String sessionId = TokenUtils.getToken();
        ops.set(sessionId, data == null ? "null" : data, expiredIn, TimeUnit.SECONDS);
        returnJson.put("errcode", 0);
        returnJson.put("errmsg", "success");
        returnJson.put("sessionId", sessionId);
        returnJson.put("expired_in", expiredIn);
        return returnJson;
    }

    public JSONObject getSessionInfo(String sessionId) {
        JSONObject returnJson = new JSONObject();

        ValueOperations<String, String> ops = template.opsForValue();
        String sessionInfo = ops.get(sessionId);
        if (sessionInfo == null) {
            // 两种情况：sessionId 不存在，sessionId 已过期。这里统一当作过期处理
            returnJson.put("errcode", 10012);
            returnJson.put("errmsg", "sessionId was expired.");
            return returnJson;
        }
        returnJson.put("errcode", 0);
        returnJson.put("errmsg", "success");
        returnJson.put("userInfo", sessionInfo);
        return returnJson;
    }
}