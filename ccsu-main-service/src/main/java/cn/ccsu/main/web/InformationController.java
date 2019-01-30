package cn.ccsu.main.web;

import cn.ccsu.main.enums.ApplyStatus;
import cn.ccsu.main.exceptions.GlobalException;
import cn.ccsu.main.pojo.po.Information;
import cn.ccsu.main.pojo.vo.BaseRes;
import cn.ccsu.main.service.ApplyService;
import cn.ccsu.main.service.InformationService;
import cn.ccsu.main.utils.BaseResUtil;
import com.fasterxml.jackson.annotation.JsonView;
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

    @Autowired
    private ApplyService applyService;

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

    // 4 查询information
    @JsonView(Information.DetailInformation.class)
    @GetMapping("/getInformationById")
    public BaseRes getInformationById(@RequestParam int id) {
        return BaseResUtil.success(informationService.getInformationById(id));
    }

    // 5 activity 活动申请
    @PostMapping("/applyActivity")
    public BaseRes activityApply(int userId, int informationId) {
        Information information = informationService.getInformationById(informationId);
        if (information == null) {
            return BaseResUtil.error(-1, "找不到information");
        }
        if (!CATEGORY_MAP.containsKey(information.getCategory())) {
            return BaseResUtil.error(-1, "不是活动，无法报名");
        }
        applyService.apply(information.getId(), userId);
        return BaseResUtil.success();
    }

    // 6 activity 更新申请状态为成功
    @GetMapping("/modifyActivityStatusSuccess")
    public BaseRes modifyStatusSuccess(int applyId) {
        applyService.updateApplyStatus(applyId, ApplyStatus.SUCCESS);
        return BaseResUtil.success();
    }

    // 7 activity 更新申请状态为成功
    @GetMapping("/modifyActivityStatusFailure")
    public BaseRes modifyStatusFailure(int applyId) {
        applyService.updateApplyStatus(applyId, ApplyStatus.FAILURE);
        return BaseResUtil.success();
    }

}
