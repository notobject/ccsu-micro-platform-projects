/*
 * Created by Long Duping
 * Date 2018/12/5 15:38
 */
package cn.ccsu.user.service.impl;

import cn.ccsu.common.cnt.Const;
import cn.ccsu.user.deps.SessionService;
import cn.ccsu.user.service.UserService;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service("miniProgramUserService")
@RefreshScope
public class MiniProgramUserServiceImpl implements UserService {
    private static final Logger log = LoggerFactory.getLogger(MiniProgramUserServiceImpl.class);

    @Value("${wx.appId: unkown}")
    private String appId;
    @Value("${wx.appSecret: unkown}")
    private String appSecret;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private SessionService sessionService;

    @Override
    public String login(String jsonParam) {
        JSONObject returnJson = new JSONObject();
        JSONObject info = JSONObject.parseObject(jsonParam);
        String code = info.getString("errcode");

        // 用code 去微信服务器拿 openId 和 session_key
        JSONObject openIdAndSessionKey = code2session(code);
        int errcode = openIdAndSessionKey.getIntValue("errcode");
        if (errcode != 0) {
            returnJson.put("errcode", 10011);
            returnJson.put("errmsg", openIdAndSessionKey.getString("errmsg"));
            log.error(returnJson.toString());
            return returnJson.toJSONString();
        }
        openIdAndSessionKey.remove("errcode");
        openIdAndSessionKey.remove("errmsg");
        // 用openId 和 session_key 去会话服务器拿到sessionId

        // 存入用户信息到会话中
        JSONObject sessionJson = JSONObject.parseObject(sessionService.newSession(openIdAndSessionKey.toString()));
        errcode = sessionJson.getIntValue("errcode");
        if (0 != errcode) {
            returnJson.put("errcode", errcode);
            returnJson.put("errmsg", sessionJson.getString("errmsg"));
            return returnJson.toString();
        }
        returnJson.put("errcode", 0);
        returnJson.put("errmsg", "success");
        // TODO 这里为测试数据，实际放入用户信息
        returnJson.put("nickName", "openid");
        returnJson.put("jwcAccount", "sessionKey");
        returnJson.put("sessionId", sessionJson.getString("sessionId"));
        return returnJson.toString();
    }

    private JSONObject code2session(String code) {
        JSONObject returnJson = new JSONObject();
        URI uri = UriComponentsBuilder.fromUriString(Const.WxApi.CODE_TO_SESSION)
                .build()
                .expand(appId, appSecret, code)
                .encode()
                .toUri();
        ResponseEntity<String> entity = restTemplate.getForEntity(uri, String.class);
        if (HttpStatus.OK == entity.getStatusCode()) {
            JSONObject res = JSONObject.parseObject(entity.getBody());
            int errcode = res.getIntValue("errcode");
            returnJson.put("errcode", errcode);
            if (0 == errcode) {
                // 请求成功
                returnJson.put("openId", res.getString("openid"));
                returnJson.put("sessionKey", res.getString("session_key"));
            } else {
                returnJson.put("errmsg", res.getString("errmsg"));
            }
            return returnJson;
        }
        returnJson.put("errcode", entity.getStatusCodeValue());
        returnJson.put("errmsg", "http connect " + uri.toString() + " failed");
        return returnJson;
    }
}
