package cn.ccsu.main.pojo.vo;

import cn.ccsu.main.pojo.po.Information;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

/**
 * @author hangs.zhang
 * @date 2019/1/25
 * *****************
 * function:
 */
@Data
public class BaseRes<T> {

    // 错误代码
    private Integer errcode;

    // 错误信息
    private String errmsg;

    @JsonView(Information.SimpleInformation.class)
    private T data;

}
