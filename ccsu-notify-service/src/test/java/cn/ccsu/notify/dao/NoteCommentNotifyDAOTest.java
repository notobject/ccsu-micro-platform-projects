package cn.ccsu.notify.dao;

import cn.ccsu.notify.NotifyServiceApplicationTests;
import cn.ccsu.notify.pojo.po.NoteCommentNotifyPO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;


/**
 * @author hangs.zhang
 * @date 2018/12/10
 * *****************
 * function:
 */
@Slf4j
public class NoteCommentNotifyDAOTest extends NotifyServiceApplicationTests {

    @Autowired
    private NoteCommentNotifyDAO noteCommentNotifyDAO;

    @Test
    public void list() {
        List<NoteCommentNotifyPO> list = noteCommentNotifyDAO.listByStatus(2, 1, 0, 5);
        log.info("size : {}", list.size());
        list.forEach(System.out::println);
    }

    @Test
    public void updateCommentNotifyStatus() {
        int update = noteCommentNotifyDAO.updateCommentNotifyStatus(2, 2, 1);
        assertEquals(1, update);
    }

    @Test
    public void selectByNotifyId() {
        NoteCommentNotifyPO noteCommentNotify = noteCommentNotifyDAO.selectByNotifyId(2);
        log.info("data : {}", noteCommentNotify);
    }

    @Test
    public void insertNotify() {
        NoteCommentNotifyPO noteCommentNotifyPO = new NoteCommentNotifyPO();
        noteCommentNotifyPO.setAuthorId(1);
        noteCommentNotifyPO.setMessage("你好，可以叫我的微信吗？");
        noteCommentNotifyPO.setSendTime(new Date());
        noteCommentNotifyPO.setNoteId(1);
        int i = noteCommentNotifyDAO.insertNotify(noteCommentNotifyPO);
        log.info("id : {}", noteCommentNotifyPO.getNotifyId());
    }

    @Test
    public void insertNotified() {
        noteCommentNotifyDAO.insertNotified(2, Arrays.asList(2, 3, 4, 5, 6));
    }
}