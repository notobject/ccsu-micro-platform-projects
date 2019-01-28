package cn.ccsu.main.web;

import cn.ccsu.common.entity.BaseRes;
import cn.ccsu.common.util.BaseResUtil;
import cn.ccsu.main.exceptions.GlobalException;
import cn.ccsu.main.pojo.po.Information;
import cn.ccsu.main.service.InformationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static cn.ccsu.main.config.ApplicationConfig.CATEGORY_MAP;

/**
 * @author hangs.zhang
 * @date 2019/1/28
 * *****************
 * function:
 */
@RequestMapping("/information")
@RestController
public class InformationController {

    @Autowired
    private InformationService informationService;

    // 列表类目list
    @GetMapping("categoryList")
    public BaseRes categoryList() {
        return BaseResUtil.success(categoryList());
    }

    // 1 创建
    @PostMapping("/createInformation")
    public BaseRes createInformation(String title, String content, String authors, String category) {
        if (!CATEGORY_MAP.containsKey(category)) {
            throw new GlobalException(-1, "category不存在");
        }
        Information information = new Information();
        information.setTitle(title);
        information.setAuthors(authors);
        information.setContent(content);
        information.setCategory(category);
        information.setReleaseTime(new Date());
        informationService.addInformation(information);
        return BaseResUtil.success();
    }

    // 2 删除
    @PostMapping("/removeInformation")
    public BaseRes removeInformation(@RequestParam int id) {
        informationService.removeInformation(id);
        return BaseResUtil.success();
    }

    // 3 修改
    @PostMapping("/modifyInformation")
    public BaseRes modifyInformation(int id, String title, String content, String authors) {
        Information information = informationService.getInformationById(id);
        if (information == null) {
            return BaseResUtil.error(-1, "information不存在");
        }
        if (StringUtils.isNotBlank(title)) {
            information.setTitle(title);
        }
        if (StringUtils.isNotBlank(content)) {
            information.setContent(content);
        }
        if (StringUtils.isNotBlank(authors)) {
            information.setAuthors(authors);
        }
        informationService.modifyInformation(information);
        return BaseResUtil.success();
    }

    // 4 查询
    @GetMapping("/getInformationById")
    public BaseRes getInformationById(@RequestParam int id) {
        return BaseResUtil.success(informationService.getInformationById(id));
    }

}
