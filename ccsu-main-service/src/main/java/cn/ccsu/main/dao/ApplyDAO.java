package cn.ccsu.main.dao;

import cn.ccsu.main.pojo.po.InformationApply;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hangs.zhang
 * @date 2019/1/30
 * *****************
 * function: 活动申请的dao
 */
@Repository
public interface ApplyDAO {

    InformationApply selectById(int id);

    int insert(InformationApply informationApply);

    int update(@Param("id") int id, @Param("status") String status);

    List<InformationApply> listAppliesByInformationId(int informationId);

}
