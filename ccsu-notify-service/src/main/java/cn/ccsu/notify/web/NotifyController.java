package cn.ccsu.notify.web;

import cn.ccsu.notify.pojo.dto.Notify;
import cn.ccsu.notify.pojo.vo.BaseEntity;
import cn.ccsu.notify.service.NotifyService;
import cn.ccsu.notify.utils.BaseEntityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author hangs.zhang
 * @date 2018/12/10
 * *****************
 * function:
 * 处理帖子通知的controller
 * <p>
 * 先采取轮询的方式获取新通知，等后期有时间优化为websocket长连接
 */
@RestController
@RequestMapping("/notify")
public class NotifyController {

    @Autowired
    private NotifyService notifyService;

    @GetMapping("/unread")
    public BaseEntity listUnread(@RequestParam int userId,
                                 @RequestParam(required = false, defaultValue = "0") int start,
                                 @RequestParam(required = false, defaultValue = "5") int offset) {
        List<Notify> notifies = notifyService.getUnReadNotify(userId, start, offset);
        return BaseEntityUtil.success(notifies);
    }

    // 根据被通知者id 删除通知
    @GetMapping("/delete")
    public BaseEntity delete(@RequestParam Integer userId, @RequestParam Integer notifiedId) {
        return BaseEntityUtil.success();
    }

    // 根据userId获取当前用户所有通知
    @GetMapping("/list")
    public BaseEntity comments(@RequestParam int userId, @RequestParam int start, @RequestParam int offset) {
        return BaseEntityUtil.success();
    }

    // 根据notifyId读取通知内容
    @GetMapping("/content")
    public BaseEntity content(@RequestParam Integer id) {
        return BaseEntityUtil.success();
    }

}
