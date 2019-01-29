package cn.ccsu.main.service;

import cn.ccsu.main.dao.InformationDAO;
import cn.ccsu.main.exceptions.GlobalException;
import cn.ccsu.main.pojo.po.Information;
import cn.ccsu.main.utils.RedisUtil;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author hangs.zhang
 * @date 2019/1/28
 * *****************
 * function:
 */
@Service
public class InformationService {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private HotAndCacheService cacheService;

    @Autowired
    private InformationDAO informationDAO;

    // 1,创建,创建之后返回id
    public int addInformation(Information information) {
        int i = informationDAO.insert(information);
        if (i != 1) {
            throw new GlobalException(-1, "添加失败");
        }
        cacheService.addInformation2Cache(information);
        return information.getId();
    }

    // 2，删除
    public void removeInformation(int id) {
        int i = informationDAO.delete(id);
        if (i != 1) {
            throw new GlobalException(-1, "删除失败");
        }
        cacheService.removeInformationFromCache(id);
    }

    // 3，修改
    public void modifyInformation(Information information) {
        int i = informationDAO.update(information);
        if (i != 1) {
            throw new GlobalException(-1, "修改失败");
        }
        cacheService.addInformation2Cache(information);
    }

    // 4，查询指定id区域的数据
    public List<Information> list(int start, int offset) {
        return informationDAO.list(start, offset);
    }

    // 5, 查询information数量
    public int count() {
        return informationDAO.count();
    }

    // 查询指定id的数据
    public Information getInformationById(int id) {
        Information information = cacheService.getInformationFromCacheById(id);
        if (information != null) {
            cacheService.addInformationClick(id);
        }
        return information;
    }

    // 查询最新数据 10条
    public List<Information> getLatestInformation() {
        List<Information> informations = Lists.newArrayList();
        int maxId = informationDAO.maxId();
        ArrayList<Information> list = informationDAO.list(maxId - 20, maxId);
        if (list.size() > 10) {
            informations.addAll(list.subList(list.size() - 10, list.size() - 1));
        } else {
            Collections.reverse(list);
            informations.addAll(list);
        }
        return informations;
    }

    // 根据类别查询
    public List<Information> getInformationByCategory(String category, int start, int offset) {
        return informationDAO.listByCategory(category, start, offset);
    }

    // 查询热榜 10个
    public List<Information> getHotInformation() {
        return cacheService.getHotInformation();
    }

}
