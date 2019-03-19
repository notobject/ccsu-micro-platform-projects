package cn.ccsu.main.web;

import cn.ccsu.main.pojo.vo.BaseRes;
import cn.ccsu.main.utils.BaseResUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static cn.ccsu.main.config.ApplicationConfig.FUNCTION1;
import static cn.ccsu.main.config.ApplicationConfig.FUNCTION2;

/**
 * @author hangs.zhang
 * @date 2019/1/29
 * *****************
 * function:
 */
@Api("首页功能条")
@RequestMapping("/homePage")
@RestController
public class HomePageController {

    @ApiOperation("第一条的功能")
    @GetMapping("/firstList")
    public BaseRes firstList() {
        return BaseResUtil.success(FUNCTION1);
    }

    @ApiOperation("第二条的功能")
    @GetMapping("/secondList")
    public BaseRes secondList() {
        return BaseResUtil.success(FUNCTION2);
    }

}
