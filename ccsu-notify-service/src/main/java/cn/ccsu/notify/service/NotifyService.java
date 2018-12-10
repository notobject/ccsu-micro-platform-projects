package cn.ccsu.notify.service;

import cn.ccsu.notify.pojo.dto.Notify;

import java.util.List;

/**
 * @author hangs.zhang
 * @date 2018/12/10
 * *****************
 * function:
 * 处理活动，招聘的通知的服务
 */
public interface NotifyService {

    // 获取未读通知
    List<Notify> getUnReadNotify(int userId, int start, int offset);

    // 获取当前用户所有通知
    List<Notify> getAllNotify(int userId, int start, int offset);

    // 删除通知 即修改状态
    boolean removeNotify(int userId, int notifyId);

}
