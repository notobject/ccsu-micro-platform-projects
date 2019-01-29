package cn.ccsu.main.web;

import cn.ccsu.main.pojo.vo.BaseRes;
import cn.ccsu.main.utils.BaseResUtil;
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
@RequestMapping("/homePage")
@RestController
public class HomePageController {

    @GetMapping("/firstList")
    public BaseRes firstList() {
        return BaseResUtil.success(FUNCTION1);
    }

    @GetMapping("/secondList")
    public BaseRes secondList() {
        return BaseResUtil.success(FUNCTION2);
    }

}
