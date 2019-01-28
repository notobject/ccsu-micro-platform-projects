package cn.ccsu.team.web;

import cn.ccsu.common.entity.BaseRes;
import cn.ccsu.common.util.BaseResUtil;
import cn.ccsu.team.pojo.po.ProjectPO;
import cn.ccsu.team.pojo.vo.GroupMemberVO;
import cn.ccsu.team.pojo.vo.TeamVO;
import cn.ccsu.team.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hangs.zhang
 * @date 2019/1/25
 * *****************
 * function:
 */
@RequestMapping("/team")
@RestController
public class TeamController {

    @Autowired
    private TeamService teamService;

    //1，根据userId查询团队数据
    @GetMapping("/getTeamByUserId")
    public BaseRes getTeamByUserId(@RequestParam int userId) {
        List<TeamVO> teamVOS = teamService.getTeamByUserId(userId);
        return BaseResUtil.success(teamVOS);
    }

    //2，根据teamId查询团队数据
    @GetMapping("/getTeamByTeamId")
    public BaseRes getTeamByTeamId(@RequestParam int teamId) {
        TeamVO teamById = teamService.getTeamByTeamId(teamId);
        return BaseResUtil.success(teamById);
    }

    //3，成员的curd

    // 增加成员
    @GetMapping("/addMember2Team")
    public BaseRes addMember2Team(@RequestParam int teamId, @ModelAttribute GroupMemberVO groupMemberVO) {
        teamService.addMember2Team(teamId, groupMemberVO);
        return BaseResUtil.success();
    }

    // 增加项目user
    @GetMapping("/addUser2Team")
    public BaseRes addUser2Team(int teamId, int userId) {
        teamService.addUser2Team(teamId, userId);
        return BaseResUtil.success();
    }

    // 直接更新
    @GetMapping("/updateMember2Team")
    public BaseRes updateMember2Team(@RequestParam int teamId, @RequestParam ArrayList<GroupMemberVO> groupMemberVOS) {
        teamService.updateMember2Team(teamId, groupMemberVOS);
        return BaseResUtil.success();
    }

    //4，项目的curd

    // 创建project，然后添加到team
    @GetMapping("/addProject2Team")
    public BaseRes addProject2Team(@RequestParam int teamId, String projectName, String projectDescription) {
        ProjectPO projectPO = new ProjectPO();
        projectPO.setDescription(projectDescription);
        projectPO.setName(projectName);
        teamService.addProject2Team(teamId, projectPO);
        return BaseResUtil.success();
    }

    //5，增加荣誉
    @GetMapping("/addHonor2Team")
    public BaseRes addHonor2Team(int teamId, String honor) {
        teamService.addHonor2Team(teamId, honor);
        return BaseResUtil.success();
    }

    //6，追加日志
    @GetMapping("/addLog2Team")
    public BaseRes addLog2Team(int teamId, String log) {
        teamService.addLog2Team(teamId, log);
        return BaseResUtil.success();
    }


}
