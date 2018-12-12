package cn.ccsu.notify.service.impl;

import cn.ccsu.notify.dao.SystemNotifyDAO;
import cn.ccsu.notify.dao.NoteCommentNotifyDAO;
import cn.ccsu.notify.enums.NotifyStatus;
import cn.ccsu.notify.enums.NotifyType;
import cn.ccsu.notify.pojo.dto.SystemNotifyDTO;
import cn.ccsu.notify.pojo.dto.NoteCommentNotifyDTO;
import cn.ccsu.notify.pojo.dto.Notify;
import cn.ccsu.notify.pojo.po.SystemNotifyPO;
import cn.ccsu.notify.pojo.po.NoteCommentNotifyPO;
import cn.ccsu.notify.service.NotifyService;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hangs.zhang
 * @date 2018/12/10
 * *****************
 * function:
 */
@Service
public class NotifyServiceImpl implements NotifyService {

    @Autowired
    private SystemNotifyDAO systemNotifyDAO;

    @Autowired
    private NoteCommentNotifyDAO noteCommentNotifyDAO;

    @Override
    public void storageNotify(Notify notify, List<Integer> userIds, NotifyType notifyType) {
        if (!NotifyType.comment.equals(notifyType)) {
            SystemNotifyPO systemNotifyPO = new SystemNotifyPO();
            SystemNotifyDTO systemNotifyDTO = (SystemNotifyDTO) notify;
            BeanUtils.copyProperties(systemNotifyDTO, systemNotifyPO);
            systemNotifyDAO.insertNotify(systemNotifyPO);
            systemNotifyDAO.insertNotified(systemNotifyPO.getNotifyId(), userIds);
        } else {
            NoteCommentNotifyPO noteCommentNotifyPO = new NoteCommentNotifyPO();
            NoteCommentNotifyDTO noteCommentNotifyDTO = (NoteCommentNotifyDTO) notify;
            BeanUtils.copyProperties(noteCommentNotifyDTO, noteCommentNotifyPO);
            noteCommentNotifyDAO.insertNotify(noteCommentNotifyPO);
            noteCommentNotifyDAO.insertNotified(noteCommentNotifyPO.getNotifyId(), userIds);
        }
    }

    @Override
    public List<Notify> getUnReadNotify(int userId, int start, int offset) {
        List<SystemNotifyPO> systemNotifyPOS = systemNotifyDAO.listByStatus(userId, NotifyStatus.unread.getCode(), start, offset);
        List<NoteCommentNotifyPO> noteCommentNotifyPOS = noteCommentNotifyDAO.listByStatus(userId, NotifyStatus.unread.getCode(), start, offset);
        List<Notify> result = Lists.newArrayList();
        systemNotifyPOS.forEach(e -> {
            SystemNotifyDTO systemNotifyDTO = new SystemNotifyDTO();
            BeanUtils.copyProperties(e, systemNotifyDTO);
            systemNotifyDTO.setNotifyType("system");
            result.add(systemNotifyDTO);
        });
        noteCommentNotifyPOS.forEach(e -> {
            NoteCommentNotifyDTO noteCommentNotifyDTO = new NoteCommentNotifyDTO();
            BeanUtils.copyProperties(e, noteCommentNotifyDTO);
            noteCommentNotifyDTO.setNotifyType("noteComment");
            result.add(noteCommentNotifyDTO);
        });
        return result.stream()
                .sorted((o1, o2) -> (int) (o2.getSendTime().getTime() - o1.getSendTime().getTime()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Notify> getAllNotify(int userId, int start, int offset) {
        List<SystemNotifyPO> systemNotifyPOS = systemNotifyDAO.listAll(userId, start, offset);
        List<NoteCommentNotifyPO> noteCommentNotifyPOS = noteCommentNotifyDAO.listAll(userId, start, offset);
        List<Notify> result = Lists.newArrayList();
        systemNotifyPOS.forEach(e -> {
            SystemNotifyDTO systemNotifyDTO = new SystemNotifyDTO();
            BeanUtils.copyProperties(e, systemNotifyDTO);
            systemNotifyDTO.setNotifyType("system");
            result.add(systemNotifyDTO);
        });
        noteCommentNotifyPOS.forEach(e -> {
            NoteCommentNotifyDTO noteCommentNotifyDTO = new NoteCommentNotifyDTO();
            BeanUtils.copyProperties(e, noteCommentNotifyDTO);
            noteCommentNotifyDTO.setNotifyType("noteComment");
            result.add(noteCommentNotifyDTO);
        });
        return result.stream()
                .sorted((o1, o2) -> (int) (o2.getSendTime().getTime() - o1.getSendTime().getTime()))
                .collect(Collectors.toList());
    }

    @Override
    public Notify getNotifyContent(int userId, int notifyId, NotifyType notifyType) {
        Notify result = null;
        if (!NotifyType.comment.equals(notifyType)) {
            SystemNotifyDTO systemNotifyDTO = new SystemNotifyDTO();
            // 查询通知内容
            SystemNotifyPO systemNotifyPO = systemNotifyDAO.selectByNotifyId(notifyId);
            // 将status置为已读
            systemNotifyDAO.updateNotifyStatus(notifyId, userId, NotifyStatus.read.getCode());
            BeanUtils.copyProperties(systemNotifyPO, systemNotifyDTO);
            systemNotifyDTO.setNotifyType("system");
            result = systemNotifyDTO;
        } else {
            NoteCommentNotifyDTO noteCommentNotifyDTO = new NoteCommentNotifyDTO();
            // 查询通知内容
            NoteCommentNotifyPO noteCommentNotifyPO = noteCommentNotifyDAO.selectByNotifyId(notifyId);
            // 将status置为已读
            noteCommentNotifyDAO.updateCommentNotifyStatus(notifyId, userId, NotifyStatus.read.getCode());
            // 包装
            BeanUtils.copyProperties(noteCommentNotifyPO, noteCommentNotifyDTO);
            noteCommentNotifyDTO.setNotifyType("noteComment");
            result = noteCommentNotifyDTO;
        }
        return result;
    }

    @Override
    public boolean removeNotify(int userId, int notifyId, NotifyType notifyType) {
        boolean result = false;
        if (!NotifyType.comment.equals(notifyType)) {
            result = systemNotifyDAO.updateNotifyStatus(notifyId, userId, NotifyStatus.delete.getCode()) == 1;
        } else {
            result = noteCommentNotifyDAO.updateCommentNotifyStatus(notifyId, userId, NotifyStatus.delete.getCode()) == 1;
        }
        return result;
    }
}
