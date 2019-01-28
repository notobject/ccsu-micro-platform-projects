package cn.ccsu.team.pojo.po;

import lombok.Data;

/**
 * @author hangs.zhang
 * @date 2019/1/26
 * *****************
 * function:
 */
@Data
public class ProjectPO {

    private int id;

    private String name;

    // 项目描述
    private String description;

    // 项目荣誉 使用,切割
    private String honor;

    // 项目性质
    private String properties;

    // 项目进度 使用,切割
    private String schedule;

}
