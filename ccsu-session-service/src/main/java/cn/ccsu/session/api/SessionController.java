/*
 * Created by Long Duping
 * Date 2018/12/5 12:35
 */
package cn.ccsu.session.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class SessionController {

    @Autowired
    StringRedisTemplate template;

    //若会话过期则续约
    
    //获取会话信息

    public String set(String key, String value) {
        ValueOperations<String, String> ops = template.opsForValue();
        ops.set(key, value);
        return "aa";
    }

    public String get(String key) {
        ValueOperations<String, String> ops = template.opsForValue();
        return ops.get(key);
    }

    public static void main(String[] args) {
    }

}
