package cn.ccsu.notify.dao;

import cn.ccsu.notify.NotifyServiceApplicationTests;
import cn.ccsu.notify.enums.SystemNotifyType;
import cn.ccsu.notify.pojo.po.SystemNotifyPO;
import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author hangs.zhang
 * @date 2018/12/12
 * *****************
 * function:
 */
@Slf4j
public class SystemNotifyDAOTest extends NotifyServiceApplicationTests {

    @Autowired
    private SystemNotifyDAO systemNotifyDAO;

    @Test
    public void insertNotify() {
        SystemNotifyPO systemNotifyPO = new SystemNotifyPO();
        systemNotifyPO.setActivityName("爱生活，爱技术");
        systemNotifyPO.setMessage("您好，我是后台审核人员............");
        systemNotifyPO.setSendTime(new Date());
        systemNotifyPO.setSystemNotifyType(SystemNotifyType.activity.toString());
        int i = systemNotifyDAO.insertNotify(systemNotifyPO);
        log.info("i : {}", i);
        log.info("id : {}", systemNotifyPO.getNotifyId());
    }

    @Test
    public void insertNotified() {
        List<Integer> userIds = Arrays.asList(2, 3, 4, 5, 6);
        systemNotifyDAO.insertNotified(20, userIds);
    }

    @Test
    public void listByStatus() {
        List<SystemNotifyPO> systemNotifyPOS = systemNotifyDAO.listByStatus(2, 0, 0, 5);
        systemNotifyPOS.forEach(System.out::println);
    }

    @Test
    public void listAll() {
        List<SystemNotifyPO> systemNotifyPOS = systemNotifyDAO.listAll(2, 0, 5);
        systemNotifyPOS.forEach(System.out::println);
    }

    @Test
    public void updateNotifyStatus() {
        systemNotifyDAO.updateNotifyStatus(20, 2, 1);
    }

    @Test
    public void selectByNotifyId() {
        SystemNotifyPO systemNotifyPO = systemNotifyDAO.selectByNotifyId(20);
        System.out.println(systemNotifyPO);
    }
}