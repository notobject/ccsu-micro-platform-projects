package cn.ccsu.team.web;

import cn.ccsu.team.pojo.vo.BaseRes;
import cn.ccsu.team.pojo.vo.ProjectVO;
import cn.ccsu.team.pojo.vo.TeamVO;
import cn.ccsu.team.service.TeamService;
import cn.ccsu.team.utils.BaseResUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * @author hangs.zhang
 * @date 2019/1/25
 * *****************
 * function:
 */
@RequestMapping("/homePage")
@RestController
public class HomePageController {

    @Autowired
    private TeamService teamService;

    // 根据userId返回teamList，projectList
    @GetMapping("/getTeamHomePage")
    public BaseRes getTeamHomePage(String userInfo) {
        JSONObject info = JSONObject.parseObject(userInfo).getJSONObject("userInfo");
        HashMap<String, Object> result = Maps.newHashMap();
        List<TeamVO> teams = teamService.getTeamByUserId(info.getString("openId"));
        result.put("teams", teams);
        Set<ProjectVO> projects = Sets.newHashSet();
        teams.forEach(e -> projects.addAll(e.getProjects()));
        result.put("projects", projects);
        return BaseResUtil.success(result);
    }

    @GetMapping("/openId")
    public String openId(String userInfo) {
        JSONObject info = JSONObject.parseObject(userInfo).getJSONObject("userInfo");
        return info.getString("openId");
    }

}
