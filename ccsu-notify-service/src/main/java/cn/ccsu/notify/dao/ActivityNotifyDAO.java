package cn.ccsu.notify.dao;

import cn.ccsu.notify.pojo.po.ActivityNotifyPO;
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
public interface ActivityNotifyDAO {

    // 获取评论列表页
    List<ActivityNotifyPO> list(@Param("userId") int userId, @Param("status") int status,
                                @Param("start") int start, @Param("offset") int offset);

    int updateActivityNotifyStatus(@Param("notifyId") int notifyId, @Param("notifiedUserId") int notifiedUserId,
                                   @Param("status") int status);

    ActivityNotifyPO selectByNotifyId(@Param("notifyId") int notifyId);

}
