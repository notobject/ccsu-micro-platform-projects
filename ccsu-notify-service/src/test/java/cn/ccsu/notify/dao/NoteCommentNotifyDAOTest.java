package cn.ccsu.notify.dao;

import cn.ccsu.notify.NotifyServiceApplicationTests;
import cn.ccsu.notify.pojo.po.NoteCommentNotifyPO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
        List<NoteCommentNotifyPO> list = noteCommentNotifyDAO.list(2, 1, 0, 5);
        log.info("size : {}", list.size());
        list.forEach(System.out::println);
    }

    @Test
    public void updateCommentNotifyStatus() {
        int update = noteCommentNotifyDAO.updateCommentNotifyStatus(1, 2, 1);
        assertEquals(1, update);
    }

    @Test
    public void selectByNotifyId() {
        NoteCommentNotifyPO noteCommentNotify = noteCommentNotifyDAO.selectByNotifyId(1);
        log.info("data : {}", noteCommentNotify);
    }
}