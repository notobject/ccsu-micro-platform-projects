/*
 * Created by Long Duping
 * Date 2018/12/5 15:30
 */
package cn.ccsu.common.cnt;

public final class Const {
    public static final boolean DEBUG = true;

    public static final class PlatformType {
        public static final String MINI_PROGRAM = "MiniProgram";
        public static final String Android = "Android";
        public static final String IOS = "IOS";
    }

    public static final class WxApi {
        public static final String CODE_TO_SESSION = "https://web.weixin.qq.com/sns/jscode2session?appid={appId}&secret={appSecret}&js_code={errcode}&grant_type=authorization_code";
    }
}
