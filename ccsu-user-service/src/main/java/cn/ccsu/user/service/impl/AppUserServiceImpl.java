/*
 * Created by Long Duping
 * Date 2018/12/5 15:47
 */
package cn.ccsu.user.service.impl;

import cn.ccsu.user.entity.UserInfo;
import cn.ccsu.user.service.UserService;
import org.springframework.stereotype.Service;

@Service("appUserService")
public class AppUserServiceImpl implements UserService {
    @Override
    public UserInfo login(String jsonParam) {
        throw new RuntimeException("暂未实现基于其它平台的登录方式。");
    }
}
