package cn.ccsu.team.web;

import cn.ccsu.common.entity.BaseRes;
import cn.ccsu.common.util.BaseResUtil;
import cn.ccsu.team.pojo.vo.ProjectVO;
import cn.ccsu.team.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hangs.zhang
 * @date 2019/1/25
 * *****************
 * function:
 */
@RequestMapping("/project")
@RestController
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    // 1，根据projectId查询项目
    @GetMapping("/getProjectByProjectId")
    public BaseRes getProjectByProjectId(@RequestParam int projectId) {
        ProjectVO projectVO = projectService.getProjectByProjectId(projectId);
        return BaseResUtil.success(projectVO);
    }

    // 2，基本信息管理，即修改基本信息
    @GetMapping("/updateProject")
    public BaseRes updateProject(int projectId, String name, String description, String properties) {
        projectService.updateProject(projectId, name, description, properties);
        return BaseResUtil.success();
    }

    // 3，增加honor
    @GetMapping("/addHonor2Project")
    public BaseRes addHonor2Project(int projectId, String honor) {
        projectService.addHonor2Project(projectId, honor);
        return BaseResUtil.success();
    }

    // 4，追加最新进展
    @GetMapping("/addSchedule2Project")
    public BaseRes addSchedule2Project(int projectId, String schedule) {
        projectService.addSchedule2Project(projectId, schedule);
        return BaseResUtil.success();
    }

}
