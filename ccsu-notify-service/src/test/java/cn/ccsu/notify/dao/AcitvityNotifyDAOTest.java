package cn.ccsu.notify.dao;

import cn.ccsu.notify.NotifyServiceApplicationTests;
import cn.ccsu.notify.pojo.po.SystemNotifyPO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author hangs.zhang
 * @date 2018/12/10
 * *****************
 * function:
 */
@Slf4j
public class AcitvityNotifyDAOTest extends NotifyServiceApplicationTests {

    @Autowired
    private SystemNotifyDAO acitvityNotifyDAO;

    @Test
    public void list() {
        List<SystemNotifyPO> list = acitvityNotifyDAO.listByStatus(2, 0, 0, 5);
        list.forEach(System.out::println);
    }

    @Test
    public void updateActivityNotifyStatus() {
        acitvityNotifyDAO.updateActivityNotifyStatus(1, 2, 1);
    }

    @Test
    public void selectByNotifyId() {
        SystemNotifyPO activityNotifyPO = acitvityNotifyDAO.selectByNotifyId(1);
        log.info("data : {}", activityNotifyPO);
    }
}