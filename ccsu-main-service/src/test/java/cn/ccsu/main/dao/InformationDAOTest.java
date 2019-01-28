package cn.ccsu.main.dao;

import cn.ccsu.main.CcsuMainServiceApplicationTests;
import cn.ccsu.main.pojo.po.Information;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author hangs.zhang
 * @date 2019/1/28
 * *****************
 * function:
 */
public class InformationDAOTest extends CcsuMainServiceApplicationTests {

    @Autowired
    private InformationDAO informationDAO;

    @Test
    public void insert() {
        Information informationPO = new Information();
        informationPO.setAuthors("张航 曹孝双");
        informationPO.setCategory("activity");
        informationPO.setContent("这是一大片的富文本编辑器");
        informationPO.setReleaseTime(new Date());
        informationPO.setTitle("文章标题");
        int i = informationDAO.insert(informationPO);
        Assert.assertEquals(1, i);
    }

    @Test
    public void delete() {
        int i = informationDAO.delete(1);
    }

    @Test
    public void update() {
        Information informationPO = informationDAO.selectById(2);
        informationPO.setContent("这是我修改后的content");
        int i = informationDAO.update(informationPO);
        Assert.assertEquals(1, i);
    }

    @Test
    public void selectById() {
        Information informationPO = informationDAO.selectById(2);
        System.out.println(informationPO);
    }

    @Test
    public void list() {
        List<Information> list = informationDAO.list(0, 2);
        list.forEach(System.out::println);
    }

    @Test
    public void count() {
        int count = informationDAO.count();
        System.out.println(count);
    }

    @Test
    public void maxId() {
        int id = informationDAO.maxId();
        System.out.println(id);
    }

    @Test
    public void listByCategory() {
        ArrayList<Information> activity = informationDAO.listByCategory("activity", 0, 1);
        activity.forEach(System.out::println);
    }
}