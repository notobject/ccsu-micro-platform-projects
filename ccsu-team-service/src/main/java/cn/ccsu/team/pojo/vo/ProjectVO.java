package cn.ccsu.team.pojo.vo;

import lombok.Data;

import java.util.List;
import java.util.Objects;

/**
 * @author hangs.zhang
 * @date 2019/1/25
 * *****************
 * function:
 */
@Data
public class ProjectVO {

    private int id;

    private String name;

    private String description;

    // 所属团队
    private List<String> teams;

    // 项目荣誉
    private List<String> honors;

    // 项目性质
    private String properties;

    // 进展
    private List<String> schedule;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectVO projectVO = (ProjectVO) o;
        return id == projectVO.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
