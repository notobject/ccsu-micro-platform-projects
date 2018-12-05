/*
 * Created by Long Duping
 * Date 2018/12/5 15:38
 */
package cn.ccsu.user.service.impl;

import cn.ccsu.common.cnt.Const;
import cn.ccsu.user.entity.UserInfo;
import cn.ccsu.user.service.UserService;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAlias;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
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

    @Override
    public UserInfo login(String jsonParam) {
        JSONObject info = JSONObject.parseObject(jsonParam);
        String code = info.getString("code");

        // 用code 去微信服务器拿 openId 和 session_key
        JSONObject openIdAndSessionKey = code2session(code);
        int errcode = openIdAndSessionKey.getIntValue("errcode");
        if (errcode != 0) {
            log.error("login", errcode + ":" + openIdAndSessionKey.getString("errmsg"));
            return null;
        }
        // 用openId 和 session_key 去会话服务器拿到session


        // 返回session
        return null;
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
            if (errcode == 0) {
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
