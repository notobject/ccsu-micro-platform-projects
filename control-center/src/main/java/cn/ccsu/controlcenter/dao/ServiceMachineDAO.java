/*
 * Created by Long Duping
 * Date 2019-05-09 13:49
 */
package cn.ccsu.controlcenter.dao;

import cn.ccsu.controlcenter.pojo.ServiceMachineInfo;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface ServiceMachineDAO extends Mapper<ServiceMachineInfo> {
}
