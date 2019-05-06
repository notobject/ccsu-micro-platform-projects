/*
 * Created by Long Duping
 * Date 2019-05-05 11:35
 */
package cn.ccsu.controlcenter.service;

import cn.ccsu.controlcenter.dao.UserDAO;
import cn.ccsu.controlcenter.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public boolean add(UserInfo user) {
        user.setCreateTime(new Date(System.currentTimeMillis()));
        return userDAO.insert(user) == 1;
    }

    public List<UserInfo> getUsers() {
        return userDAO.selectAll();
    }

    public boolean delete(int id) {
        return userDAO.deleteByPrimaryKey(id) == 1;
    }

    public UserInfo login(String username, String password) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setPassword(password);
        UserInfo user = userDAO.selectOne(userInfo);
        if (user != null)
            user.setPassword("");
        return user;
    }
}
