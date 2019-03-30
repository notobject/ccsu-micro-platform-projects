package cn.ccsu.team.dao;

import cn.ccsu.team.pojo.po.ProjectPO;
import cn.ccsu.team.pojo.po.TeamPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hangs.zhang
 * @date 2019/1/26
 * *****************
 * function:
 */
@Repository
public interface TeamDAO {

    int insertTeam(TeamPO teamPO);

    int insert2TeamUser(@Param("teamId") int teamId, @Param("userId") String userId);

    int insert2TeamProject(@Param("teamId") int teamId, @Param("projectId") int projectId);

    TeamPO selectByTeamId(int teamId);

    boolean updateTeam(TeamPO teamPO);

    boolean deleteTeamByTeamId(int teamId);

    List<ProjectPO> selectProjectByTeamId(int teamId);

    List<TeamPO> selectTeamByProjectId(int projectId);

    List<TeamPO> selectTeamByUserId(String userId);

}
