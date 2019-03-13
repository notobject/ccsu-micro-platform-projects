/*
 * Created by Long Duping
 * Date 2018/12/5 12:41
 */
package cn.ccsu.user.util;

import java.util.UUID;

public final class TokenUtils {

    private final static String PREFIX = "CCSU.MICRO.PLATFORM.SESSION.";

    public static synchronized String getToken() {
        return PREFIX + UUID.randomUUID().toString().toUpperCase();
    }

}
