package cn.ccsu.team.dao;

import cn.ccsu.team.CcsuTeamServiceApplicationTests;
import cn.ccsu.team.pojo.po.ProjectPO;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @author hangs.zhang
 * @date 2019/1/26
 * *****************
 * function:
 */
public class ProjectDAOTest extends CcsuTeamServiceApplicationTests {

    @Autowired
    private ProjectDAO projectDAO;

    @Test
    public void insertProject() {
        ProjectPO projectPO = new ProjectPO();
        projectPO.setName("test_project");
        projectPO.setDescription("test_description");
        int i = projectDAO.insertProject(projectPO);
        System.out.println(projectPO.getId());
    }

    @Test
    public void selectByProjectId() {
        ProjectPO projectPO = projectDAO.selectByProjectId(1);
        System.out.println(projectPO);
    }

    @Test
    public void updateProject() {
        ProjectPO projectPO = projectDAO.selectByProjectId(1);
        projectPO.setDescription("2019.1.25 创建,2019.1.26 张航加入");
        int i = projectDAO.updateProject(projectPO);
        Assert.assertEquals(1, i);
    }

}