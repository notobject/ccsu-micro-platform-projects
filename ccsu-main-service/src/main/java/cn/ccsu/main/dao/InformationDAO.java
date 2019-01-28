package cn.ccsu.main.dao;

import cn.ccsu.main.pojo.po.Information;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @author hangs.zhang
 * @date 2019/1/28
 * *****************
 * function:
 */
@Repository
public interface InformationDAO {

    int insert(Information information);

    int delete(int id);

    int update(Information information);

    Information selectById(int id);

    ArrayList<Information> list(@Param("start") int start, @Param("offset") int offset);

    ArrayList<Information> listByCategory(@Param("category") String category, @Param("start") int start,
                                          @Param("offset") int offset);

    int count();

    int maxId();

}
