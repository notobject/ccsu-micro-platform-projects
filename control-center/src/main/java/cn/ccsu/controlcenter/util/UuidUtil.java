/*
 * Created by Long Duping
 * Date 2019-05-06 22:33
 */
package cn.ccsu.controlcenter.util;

public class UuidUtil {
    public static String get() {
        return java.util.UUID.randomUUID().toString().replace("-", "");
    }
}
