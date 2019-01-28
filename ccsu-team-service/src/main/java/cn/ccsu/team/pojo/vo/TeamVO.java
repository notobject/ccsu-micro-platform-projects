package cn.ccsu.team.pojo.vo;

import lombok.Data;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author hangs.zhang
 * @date 2019/1/25
 * *****************
 * function:
 */
@Data
public class TeamVO {

    private int id;

    private String name;

    // 组员
    private List<GroupMemberVO> groupMemberVOS;

    // 团队项目
    private List<ProjectVO> projects;

    // 团队荣誉
    private List<String> honor;

    // 团队日志
    private LinkedHashMap<Date, String> teamLog;

}
