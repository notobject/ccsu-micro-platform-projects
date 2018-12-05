/*
 * Created by Long Duping
 * Date 2018/12/5 14:35
 */
package cn.ccsu.user.service;

import cn.ccsu.user.entity.UserInfo;

public interface UserService {

    public UserInfo login(String jsonParam);
}
