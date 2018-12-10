package cn.ccsu.notify.service.impl;

import cn.ccsu.notify.dao.ActivityNotifyDAO;
import cn.ccsu.notify.dao.NoteCommentNotifyDAO;
import cn.ccsu.notify.enums.NotifyStatus;
import cn.ccsu.notify.pojo.dto.ActivityNotifyDTO;
import cn.ccsu.notify.pojo.dto.NoteCommentNotifyDTO;
import cn.ccsu.notify.pojo.dto.Notify;
import cn.ccsu.notify.pojo.po.ActivityNotifyPO;
import cn.ccsu.notify.pojo.po.NoteCommentNotifyPO;
import cn.ccsu.notify.service.NotifyService;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author hangs.zhang
 * @date 2018/12/10
 * *****************
 * function:
 */
@Service
public class NotifyServiceImpl implements NotifyService {

    @Autowired
    private ActivityNotifyDAO activityNotifyDAO;

    @Autowired
    private NoteCommentNotifyDAO noteCommentNotifyDAO;

    @Override
    public List<Notify> getUnReadNotify(int userId, int start, int offset) {
        List<ActivityNotifyPO> activityNotifyPOS = activityNotifyDAO.list(userId, NotifyStatus.unread.getCode(), start, offset);
        List<NoteCommentNotifyPO> noteCommentNotifyPOS = noteCommentNotifyDAO.list(userId, NotifyStatus.unread.getCode(), start, offset);
        List<Notify> result = Lists.newArrayList();
        activityNotifyPOS.forEach(e -> {
            ActivityNotifyDTO activityNotifyDTO = new ActivityNotifyDTO();
            BeanUtils.copyProperties(e, activityNotifyDTO);
            result.add(activityNotifyDTO);
        });
        noteCommentNotifyPOS.forEach(e -> {
            NoteCommentNotifyDTO noteCommentNotifyDTO = new NoteCommentNotifyDTO();
            BeanUtils.copyProperties(e, noteCommentNotifyDTO);
            result.add(noteCommentNotifyDTO);
        });
        return result.stream()
                .sorted((o1, o2) -> (int) (o1.getSendTime().getTime() - o2.getSendTime().getTime()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Notify> getAllNotify(int userId, int start, int offset) {
        return null;
    }

    @Override
    public boolean removeNotify(int userId, int notifyId) {
        return false;
    }
}
