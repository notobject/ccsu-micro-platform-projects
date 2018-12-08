/*
 * Created by Long Duping
 * Date 2018/12/6 14:18
 */
package cn.ccsu.common.entity;

import com.sun.istack.internal.NotNull;

import java.io.Serializable;

/**
 * 业务统一请求头
 */
public class RequestHeader implements Serializable {

    private String appPlatform;         // 请求客户端平台名称
    private String appVersion;          // 应用版本号
    private String sessionId;           // 会话ID

    private PositionInfo positionInfo;  // 位置信息
    private SystemInfo systemInfo;      // 系统信息

    public String getAppPlatform() {
        return appPlatform;
    }

    public void setAppPlatform(String appPlatform) {
        this.appPlatform = appPlatform;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public PositionInfo getPositionInfo() {
        return positionInfo;
    }

    public void setPositionInfo(PositionInfo positionInfo) {
        this.positionInfo = positionInfo;
    }

    public SystemInfo getSystemInfo() {
        return systemInfo;
    }

    public void setSystemInfo(SystemInfo systemInfo) {
        this.systemInfo = systemInfo;
    }
}
