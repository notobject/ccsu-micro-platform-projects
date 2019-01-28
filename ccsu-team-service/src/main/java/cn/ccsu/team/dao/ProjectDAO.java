package cn.ccsu.team.dao;

import cn.ccsu.team.pojo.po.ProjectPO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hangs.zhang
 * @date 2019/1/26
 * *****************
 * function:
 */
@Repository
public interface ProjectDAO {

    int insertProject(ProjectPO projectPO);

    ProjectPO selectByProjectId(int id);

    int updateProject(ProjectPO projectPO);

}
