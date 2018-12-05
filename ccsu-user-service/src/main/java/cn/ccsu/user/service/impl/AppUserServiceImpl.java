/*
 * Created by Long Duping
 * Date 2018/12/5 15:47
 */
package cn.ccsu.user.service.impl;

import cn.ccsu.user.service.UserService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

@Service("appUserService")
public class AppUserServiceImpl implements UserService {
    @Override
    public String login(String jsonParam) {
        JSONObject returnJson = new JSONObject();
        returnJson.put("errcode", 10010);
        returnJson.put("errmsg", "暂未实现基于其它平台的登录方式。");
        return returnJson.toJSONString();
    }
}
