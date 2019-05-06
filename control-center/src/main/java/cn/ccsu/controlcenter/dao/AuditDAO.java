/*
 * Created by Long Duping
 * Date 2019-05-05 13:01
 */
package cn.ccsu.controlcenter.dao;

import cn.ccsu.controlcenter.pojo.AuditInfo;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface AuditDAO  extends Mapper<AuditInfo> {
}
