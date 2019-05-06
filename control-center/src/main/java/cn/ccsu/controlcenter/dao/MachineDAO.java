/*
 * Created by Long Duping
 * Date 2019-05-05 13:02
 */
package cn.ccsu.controlcenter.dao;

import cn.ccsu.controlcenter.pojo.MachineInfo;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface MachineDAO extends Mapper<MachineInfo> {
}
