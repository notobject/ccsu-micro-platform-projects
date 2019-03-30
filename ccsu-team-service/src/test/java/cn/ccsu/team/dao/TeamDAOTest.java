package cn.ccsu.team.dao;

import cn.ccsu.team.CcsuTeamServiceApplicationTests;
import cn.ccsu.team.pojo.po.ProjectPO;
import cn.ccsu.team.pojo.po.TeamPO;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author hangs.zhang
 * @date 2019/1/26
 * *****************
 * function:
 */
public class TeamDAOTest extends CcsuTeamServiceApplicationTests {

    @Autowired
    private TeamDAO teamDAO;

    @Test
    public void insertTeam() {
        TeamPO teamPO = new TeamPO();
        teamPO.setHonor("");
        teamPO.setLog("");
        teamPO.setName("test_team");
        int i = teamDAO.insertTeam(teamPO);
        Assert.assertEquals(1, i);
    }

    @Test
    public void selectByTeamId() {
        TeamPO teamPO = teamDAO.selectByTeamId(1);
        System.out.println(teamPO);
    }

    @Test
    public void updateTeamByTeamId() {
        TeamPO teamPO = teamDAO.selectByTeamId(1);
        teamPO.setLog("2019.1.25 创建团队,");
        teamPO.setHonor("暂无");
        teamDAO.updateTeam(teamPO);
    }

    @Test
    public void deleteTeamByTeamId() {
    }

    @Test
    public void selectProjectByTeamId() {
        List<ProjectPO> projectPOS = teamDAO.selectProjectByTeamId(1);
        projectPOS.forEach(System.out::println);
    }

    @Test
    public void selectTeamByUserId() {
        List<TeamPO> teamPOS = teamDAO.selectTeamByUserId("xxxxxxxx");
        teamPOS.forEach(System.out::println);
    }
}