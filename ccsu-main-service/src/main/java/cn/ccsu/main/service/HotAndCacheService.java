package cn.ccsu.main.service;

import cn.ccsu.main.dao.InformationDAO;
import cn.ccsu.main.pojo.po.Information;
import cn.ccsu.main.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static cn.ccsu.main.config.ApplicationConfig.INFORMATION_HOT_PREFIX;
import static cn.ccsu.main.config.ApplicationConfig.INFORMATION_PREFIX;

/**
 * @author hangs.zhang
 * @date 2019/1/29
 * *****************
 * function:
 */
@Service
public class HotAndCacheService {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private InformationDAO informationDAO;

    public Information getInformationFromCacheById(int id) {
        Information information = (Information) redisUtil.get(INFORMATION_PREFIX + id);
        if (information == null) {
            information = informationDAO.selectById(id);
            addInformation2Cache(information);
        }
        return information;
    }

    public void addInformation2Cache(Information information) {
        if (information != null) {
            redisUtil.set(INFORMATION_PREFIX + information.getId(), information);
        }
    }

    public void removeInformationFromCache(int id) {
        redisUtil.del(INFORMATION_PREFIX + id);
    }

    public void addInformationClick(int id) {
        redisUtil.zIncrby(INFORMATION_HOT_PREFIX, id, 1);
    }

    public List<Information> getHotInformation() {
        Set<Object> set = redisUtil.zRevrange(INFORMATION_HOT_PREFIX, 0, 10);
        return set.stream()
                .map(e -> getInformationFromCacheById((Integer) e))
                .collect(Collectors.toList());
    }

}
