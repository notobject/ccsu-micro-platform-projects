package cn.ccsu.notify.web;

import cn.ccsu.notify.enums.NotifyType;
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

    // 询问是否有更新
    @GetMapping("/ask")
    public BaseEntity ask(@RequestParam int userId) {
        List<Notify> list = notifyService.getUnReadNotify(userId, 0, 1);
        String result = "";
        if (list.size() == 0) {
            result = "没有新的通知";
        } else {
            result = "有新的通知";
        }
        return BaseEntityUtil.success(result);
    }

    @GetMapping("/unread")
    public BaseEntity listUnread(@RequestParam int userId,
                                 @RequestParam(required = false, defaultValue = "0") int start,
                                 @RequestParam(required = false, defaultValue = "5") int offset) {
        List<Notify> notifies = notifyService.getUnReadNotify(userId, start, offset);
        return BaseEntityUtil.success(notifies);
    }

    // 根据被通知者id 删除通知
    @GetMapping("/delete")
    public BaseEntity delete(@RequestParam Integer userId, @RequestParam Integer notifiedId, @RequestParam String notifyName) {
        NotifyType notifyType = NotifyType.getNotifyTypeByName(notifyName);
        boolean isDelete = notifyService.removeNotify(userId, notifiedId, notifyType);
        if (isDelete) {
            return BaseEntityUtil.success();
        } else {
            return BaseEntityUtil.error(-1, "删除失败");
        }
    }

    // 根据userId获取当前用户所有通知
    @GetMapping("/list")
    public BaseEntity list(@RequestParam int userId,
                           @RequestParam(required = false, defaultValue = "0") int start,
                           @RequestParam(required = false, defaultValue = "5") int offset) {
        List<Notify> allNotify = notifyService.getAllNotify(userId, start, offset);
        return BaseEntityUtil.success(allNotify);
    }

    // 根据notifyId与userId读取通知内容
    @GetMapping("/content")
    public BaseEntity content(@RequestParam Integer userId, @RequestParam Integer notifyId, @RequestParam String notifyName) {
        NotifyType notifyType = NotifyType.getNotifyTypeByName(notifyName);
        Notify notify = notifyService.getNotifyContent(userId, notifyId, notifyType);
        return BaseEntityUtil.success(notify);
    }

}
