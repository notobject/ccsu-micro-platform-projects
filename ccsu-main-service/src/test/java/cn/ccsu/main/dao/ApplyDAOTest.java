package cn.ccsu.main.dao;

import cn.ccsu.main.CcsuMainServiceApplicationTests;
import cn.ccsu.main.enums.ApplyStatus;
import cn.ccsu.main.pojo.po.InformationApply;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author hangs.zhang
 * @date 2019/1/30
 * *****************
 * function:
 */
public class ApplyDAOTest extends CcsuMainServiceApplicationTests {

    @Autowired
    private ApplyDAO applyDAO;

    @Test
    public void insert() {
        InformationApply apply = new InformationApply();
        apply.setInformationId(1);
        apply.setStatus(ApplyStatus.CURRENT_APPLY.name());
        apply.setUserId(1);
        int i = applyDAO.insert(apply);
        Assert.assertEquals(1, i);
    }

    @Test
    public void update() {
        InformationApply apply = applyDAO.selectById(1);
        int i = applyDAO.update(apply.getId(), ApplyStatus.SUCCESS.name());
        Assert.assertEquals(1, i);
    }

    @Test
    public void listAppliesByInformationId() {
        List<InformationApply> informationApplies = applyDAO.listAppliesByInformationId(1);
        informationApplies.forEach(System.out::println);
    }
}