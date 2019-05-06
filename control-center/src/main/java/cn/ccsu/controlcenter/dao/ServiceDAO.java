/*
 * Created by Long Duping
 * Date 2019-03-31 16:05
 */
package cn.ccsu.controlcenter.dao;

import cn.ccsu.controlcenter.pojo.ServiceInfo;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface ServiceDAO extends Mapper<ServiceInfo> {
}
