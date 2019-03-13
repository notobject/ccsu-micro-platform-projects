/*
 * Created by Long Duping
 * Date 2018/12/6 14:18
 */
package cn.ccsu.common.util;

import cn.ccsu.common.entity.RequestHeader;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 解析统一请求头
 */
public class HeaderParser {

    public static Map<String, String> parse(HttpServletRequest request) throws ClassNotFoundException {
        Map<String, String> map = new HashMap<>();
        parse(RequestHeader.class, "", request, map);
        return map.size() > 0 ? map : null;
    }

    public static Map<String, String> parse(Class clz, String prefix, HttpServletRequest request, Map<String, String> map) throws ClassNotFoundException {
        Field[] fields = clz.getDeclaredFields();
        String key = null, value;
        for (Field f : fields) {
            if (isBaseType(f.getType())) {
                // 字符串类型
                key = prefix + f.getName();
            } else {
                // 自定义类型
                parse(Class.forName(f.getType().getName()), f.getName() + ".", request, map);
            }
            value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }

    public static boolean isBaseType(Class<?> clz) {

        if (clz.equals(java.lang.Integer.class) ||
                clz.equals(int.class) ||
                clz.equals(java.lang.Byte.class) ||
                clz.equals(byte.class) ||
                clz.equals(java.lang.Long.class) ||
                clz.equals(long.class) ||
                clz.equals(java.lang.Double.class) ||
                clz.equals(double.class) ||
                clz.equals(java.lang.Float.class) ||
                clz.equals(float.class) ||
                clz.equals(java.lang.Character.class) ||
                clz.equals(char.class) ||
                clz.equals(java.lang.Short.class) ||
                clz.equals(short.class) ||
                clz.equals(java.lang.Boolean.class) ||
                clz.equals(boolean.class) || clz.equals(boolean.class) ||
                clz.equals(java.lang.String.class) ||
                clz.equals(java.util.Date.class) ||
                clz.equals(java.sql.Date.class)) {
            return true;
        }
        return false;
    }
}
