/*
 * Created by Long Duping
 * Date 2019-05-05 13:03
 */
package cn.ccsu.controlcenter.dao;

import cn.ccsu.controlcenter.pojo.TaskInfo;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface TaskDAO extends Mapper<TaskInfo> {
}
