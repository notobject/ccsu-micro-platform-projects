/*
 * Created by Long Duping
 * Date 2019-05-05 11:40
 */
package cn.ccsu.controlcenter.dao;

import cn.ccsu.controlcenter.pojo.UserInfo;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface UserDAO extends Mapper<UserInfo> {
}
