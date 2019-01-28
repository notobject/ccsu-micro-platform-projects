package cn.ccsu.main.pojo.po;

import lombok.Data;

import java.util.Date;

/**
 * @author hangs.zhang
 * @date 2019/1/28
 * *****************
 * function:
 */
@Data
public class Information {

    private int id;

    private String title;

    // 富文本
    private String content;

    // 发布时间
    private Date releaseTime;

    // 作者
    private String authors;

    // 类别，包括：活动，通知，招聘，公示
    // activity,notify,recruitment,public
    private String category;

}
