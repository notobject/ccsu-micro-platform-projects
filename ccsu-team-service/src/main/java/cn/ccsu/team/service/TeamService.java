package cn.ccsu.team.service;

import cn.ccsu.team.dao.ProjectDAO;
import cn.ccsu.team.dao.TeamDAO;
import cn.ccsu.team.pojo.po.ProjectPO;
import cn.ccsu.team.pojo.po.TeamPO;
import cn.ccsu.team.pojo.vo.GroupMemberVO;
import cn.ccsu.team.pojo.vo.ProjectVO;
import cn.ccsu.team.pojo.vo.TeamVO;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hangs.zhang
 * @date 2019/1/26
 * *****************
 * function:
 */
@Service
public class TeamService {

    @Autowired
    private TeamDAO teamDAO;

    @Autowired
    private ProjectService projectService;

    public TeamVO getTeamByTeamId(int teamId) {
        TeamPO teamPO = teamDAO.selectByTeamId(teamId);
        TeamVO teamVO = teamPO2VO(teamPO);
        return teamVO;
    }

    public List<TeamVO> getTeamByUserId(int userId) {
        List<TeamPO> teamPOS = teamDAO.selectTeamByUserId(userId);
        List<TeamVO> teamVOS = teamPOS.stream().map(teamPO -> teamPO2VO(teamPO)).collect(Collectors.toList());
        return teamVOS;
    }

    // 添加成员
    public void addMember2Team(int teamId, GroupMemberVO groupMemberVO) {
        TeamPO teamPO = teamDAO.selectByTeamId(teamId);
        String members = teamPO.getMembers();
        ArrayList<GroupMemberVO> groupMemberVOS = JSON.parseObject(members, new TypeReference<ArrayList<GroupMemberVO>>() {
        });
        groupMemberVOS.add(groupMemberVO);
        teamPO.setMembers(JSON.toJSONString(groupMemberVOS));
        teamDAO.updateTeam(teamPO);
    }

    // 直接set来update
    public void updateMember2Team(int teamId, ArrayList<GroupMemberVO> groupMemberVOS) {
        TeamPO teamPO = teamDAO.selectByTeamId(teamId);
        teamPO.setMembers(JSON.toJSONString(groupMemberVOS));
        teamDAO.updateTeam(teamPO);
    }

    // 添加userId到team
    public void addUser2Team(int teamId, int userId) {
        teamDAO.insert2TeamUser(teamId, userId);
    }

    // 添加项目到team
    public void addProject2Team(int teamId, ProjectPO projectPO) {
        ProjectPO project = projectService.addProject(projectPO);
        teamDAO.insert2TeamProject(teamId, project.getId());
    }

    public void addHonor2Team(int teamId, String honor) {
        TeamPO teamPO = teamDAO.selectByTeamId(teamId);
        String honors = teamPO.getHonor();
        if (honors != null && honors.contains(",")) {
            honors = honor;
        } else {
            honors += "," + honor;
        }
        teamPO.setHonor(honors);
        teamDAO.updateTeam(teamPO);
    }

    public void addLog2Team(int teamId, String log) {
        TeamPO teamPO = teamDAO.selectByTeamId(teamId);
        LocalDate localDate = LocalDate.now();
        log = localDate.toString() + " " + log;
        if (teamPO.getLog() != null && teamPO.getLog().length() > 0) {
            log = teamPO.getLog() + "," + log;
        }
        teamPO.setLog(log);
        teamDAO.updateTeam(teamPO);
    }

    public TeamVO teamPO2VO(TeamPO teamPO) {
        TeamVO teamVO = new TeamVO();
        String members = teamPO.getMembers();
        ArrayList<GroupMemberVO> groupMemberVOS = JSON.parseObject(members, new TypeReference<ArrayList<GroupMemberVO>>() {
        });
        teamVO.setGroupMemberVOS(groupMemberVOS);
        teamVO.setId(teamPO.getId());
        teamVO.setName(teamPO.getName());

        String honor = teamPO.getHonor();
        String[] split = honor.split(",");
        teamVO.setHonor(Arrays.asList(split));

        List<ProjectPO> projectPOS = teamDAO.selectProjectByTeamId(teamPO.getId());
        List<ProjectVO> projects = projectPOS.stream().map(projectPO -> projectPO2VO(projectPO)).collect(Collectors.toList());
        teamVO.setProjects(projects);
        return teamVO;
    }

    public ProjectVO projectPO2VO(ProjectPO projectPO) {
        ProjectVO projectVO = new ProjectVO();
        projectVO.setId(projectPO.getId());
        projectVO.setName(projectPO.getName());
        projectVO.setDescription(projectPO.getDescription());
        if (StringUtils.isNotBlank(projectPO.getHonor())) {
            projectVO.setHonors(Arrays.asList(projectPO.getHonor().split(",")));
        }
        if (StringUtils.isNotBlank(projectPO.getProperties())) {
            projectVO.setProperties(projectPO.getProperties());
        }
        List<TeamPO> teamPOS = teamDAO.selectTeamByProjectId(projectPO.getId());
        List<String> teams = teamPOS.stream().map(teamPO -> teamPO.getName()).collect(Collectors.toList());
        projectVO.setTeams(teams);

        String schedule = projectPO.getSchedule();
        if (StringUtils.isNotBlank(schedule)) {
            projectVO.setSchedule(Arrays.asList(schedule.split(",")));
        }
        return projectVO;
    }

}
