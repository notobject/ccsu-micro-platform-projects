/*
 * Created by Long Duping
 * Date 2019-02-15 14:32
 */
package cn.ccsu.user.dao;

import cn.ccsu.user.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoDAO {

    @Select("select * from user_info where openId = #{openId};")
    UserInfo selectByOpenId(@Param("openId") String openId);
}
