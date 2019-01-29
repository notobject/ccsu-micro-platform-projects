package cn.ccsu.main.utils;

import cn.ccsu.main.CcsuMainServiceApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * @author hangs.zhang
 * @date 2019/1/29
 * *****************
 * function:
 */
public class RedisUtilTest extends CcsuMainServiceApplicationTests {

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void zSet() {
        redisUtil.zAdd("zAdd-key", 3, 10);
        redisUtil.zAdd("zAdd-key", 6, 31);
        redisUtil.zAdd("zAdd-key", 7, 100);
        redisUtil.zAdd("zAdd-key", 99, 20);
        // double v = redisUtil.zIncrby("zAdd-key", 2, 3);
        // System.out.println(v);
        Set<Object> set = redisUtil.zRevrange("zAdd-key", 0, 10);
        set.forEach(e -> {
            Integer i = (Integer) e;
            System.out.println(i);
        });

    }
}