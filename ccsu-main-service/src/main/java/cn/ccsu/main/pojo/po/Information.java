package cn.ccsu.main.pojo.po;

import com.fasterxml.jackson.annotation.JsonView;
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

    public interface SimpleInformation {
    }

    public interface DetailInformation extends SimpleInformation {
    }

    @JsonView(SimpleInformation.class)
    private int id;

    @JsonView(SimpleInformation.class)
    private String title;

    // 富文本
    @JsonView(DetailInformation.class)
    private String content;

    // 发布时间
    @JsonView(SimpleInformation.class)
    private Date releaseTime;

    // 作者
    @JsonView(SimpleInformation.class)
    private String authors;

    // 类别，包括：活动，通知，招聘，公示
    // activity,notify,recruitment,public
    @JsonView(SimpleInformation.class)
    private String category;

}
