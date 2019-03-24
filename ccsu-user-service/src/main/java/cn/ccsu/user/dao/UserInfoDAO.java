/*
 * Created by Long Duping
 * Date 2019-02-15 14:32
 */
package cn.ccsu.user.dao;

import cn.ccsu.user.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoDAO {

    UserInfo selectByOpenId(@Param("openId") String openId);

    int insert(UserInfo userInfo);

    boolean isExist(@Param("openId") String openId);

    boolean updateLastLoginTime(String openId);
}
