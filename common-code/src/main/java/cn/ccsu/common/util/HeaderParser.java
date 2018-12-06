/*
 * Created by Long Duping
 * Date 2018/12/6 14:18
 */
package cn.ccsu.common.util;

import cn.ccsu.common.entity.RequestHeader;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;

/**
 * 解析统一请求头
 */
public class HeaderParser {

    public static RequestHeader parse(HttpServletRequest request) {
        Field[] fields = RequestHeader.class.getDeclaredFields();
        // TODO
        return null;
    }
}
