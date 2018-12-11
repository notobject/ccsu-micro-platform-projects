package cn.ccsu.notify.dao;

import cn.ccsu.notify.pojo.po.NoteCommentNotifyPO;
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
public interface NoteCommentNotifyDAO {

    // 列表页
    List<NoteCommentNotifyPO> listByStatus(@Param("userId") Integer userId, @Param("status") int status,
                                           @Param("start") int start, @Param("offset") int offset);

    List<NoteCommentNotifyPO> listAll(@Param("userId") Integer userId, @Param("start") int start,
                                      @Param("offset") int offset);

    // 删除通知 即修改状态
    int updateCommentNotifyStatus(@Param("notifyId") int notifyId, @Param("notifiedUserId") int notifiedUserId,
                                  @Param("status") int status);

    // 查询通知内容
    NoteCommentNotifyPO selectByNotifyId(@Param("notifyId") int notifyId);

}
