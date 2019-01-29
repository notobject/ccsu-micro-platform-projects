package cn.ccsu.main.config;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author hangs.zhang
 * @date 2019/1/29
 * *****************
 * function:
 */
public class ApplicationConfig {

    public static final LinkedHashMap<String, String> CATEGORY_MAP = Maps.newLinkedHashMap();

    public static final List<String> FUNCTION1 = Lists.newArrayList();

    public static final List<String> FUNCTION2 = Lists.newArrayList();

    public static final String INFORMATION_HOT_PREFIX = "INFORMATION_HOT_PREFIX";

    public static final String INFORMATION_PREFIX = "INFORMATION_PREFIX::";

    public static final int INFORMATION_EXPIRE = 0;

    static {
        CATEGORY_MAP.put("latest", "最新");
        CATEGORY_MAP.put("hot", "最热");
        CATEGORY_MAP.put("activity", "活动");
        CATEGORY_MAP.put("notify", "通知");
        CATEGORY_MAP.put("recruitment", "招聘");
        CATEGORY_MAP.put("public", "公示");
    }

    static {
        FUNCTION1.add("功能1");
        FUNCTION1.add("功能2");
        FUNCTION1.add("功能3");
        FUNCTION1.add("功能4");

        FUNCTION2.add("功能1");
        FUNCTION2.add("功能2");
        FUNCTION2.add("功能3");
        FUNCTION2.add("功能4");
    }

}
