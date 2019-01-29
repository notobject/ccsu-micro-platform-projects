package cn.ccsu.main.web;

import cn.ccsu.main.exceptions.GlobalException;
import cn.ccsu.main.pojo.po.Information;
import cn.ccsu.main.pojo.vo.BaseRes;
import cn.ccsu.main.service.InformationService;
import cn.ccsu.main.utils.BaseResUtil;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

import static cn.ccsu.main.config.ApplicationConfig.CATEGORY_MAP;

/**
 * @author hangs.zhang
 * @date 2019/1/28
 * *****************
 * function:
 */
@RequestMapping("/feed")
@RestController
public class FeedController {

    @Autowired
    private InformationService informationService;

    // 最新的10条
    @JsonView(Information.SimpleInformation.class)
    @GetMapping("/latest")
    public BaseRes latest() {
        List<Information> latestInformation = informationService.getLatestInformation();
        return BaseResUtil.success(latestInformation);
    }

    // 最热
    @JsonView(Information.SimpleInformation.class)
    @GetMapping("/hot")
    public BaseRes hot() {
        List<Information> hotInformation = informationService.getHotInformation();
        if (hotInformation == null || hotInformation.size() == 0) {
            return BaseResUtil.success(informationService.getLatestInformation());
        }
        return BaseResUtil.success(hotInformation);
    }

    // 根据类别查询
    @JsonView(Information.SimpleInformation.class)
    @GetMapping("/listByCategory")
    public BaseRes listByCategory(String category, int start, int offset) {
        if (!CATEGORY_MAP.containsKey(category)) {
            throw new GlobalException(-1, "category不存在");
        }
        List<Information> informations = informationService.getInformationByCategory(category, start, offset);
        Collections.reverse(informations);
        return BaseResUtil.success(informations);
    }

}
