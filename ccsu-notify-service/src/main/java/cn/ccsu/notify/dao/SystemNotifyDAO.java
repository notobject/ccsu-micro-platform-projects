package cn.ccsu.notify.dao;

import cn.ccsu.notify.pojo.po.SystemNotifyPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hangs.zhang
 * @date 2018/12/10
 * *****************
 * function:
 */
@Repository
public interface SystemNotifyDAO {

    int insertNotify(SystemNotifyPO systemNotifyPO);

    int insertNotified(@Param("notifyId") int notifyId,
                       @Param("notifyiedUserIds") List<Integer> notifyiedUserIds);

    // 获取评论列表页
    List<SystemNotifyPO> listByStatus(@Param("userId") int userId, @Param("status") int status,
                                      @Param("start") int start, @Param("offset") int offset);

    List<SystemNotifyPO> listAll(@Param("userId") int userId, @Param("start") int start,
                                 @Param("offset") int offset);

    int updateNotifyStatus(@Param("notifyId") int notifyId, @Param("notifiedUserId") int notifiedUserId,
                           @Param("status") int status);

    SystemNotifyPO selectByNotifyId(@Param("notifyId") int notifyId);

}
