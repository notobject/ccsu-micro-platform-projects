/*
 * Created by Long Duping
 * Date 2018/12/6 14:21
 */
package cn.ccsu.common.entity;

import java.io.Serializable;

public class SystemInfo implements Serializable {

    private String brand;           //手机品牌
    private String model;           //手机型号
    private int pixelRatio;         //设备像素比
    private int screenWidth;        //屏幕宽度
    private int screenHeight;       //屏幕高度
    private int windowWidth;        //可使用窗口宽度
    private int windowHeight;       //可使用窗口高度
    private int statusBarHeight;    //状态栏的高度
    private String language;        //微信设置的语言
    private String wxVersion;       //微信版本号
    private String systemVersion;   //操作系统版本
    private String platform;        //客户端平台
    private int fontSizeSetting;    //"用户字体大小设置。以“我-设置-通用-字体大小”中的设置为准，单位 px。
    private String SDKVersion;      //客户端基础库版本
    private int benchmarkLevel;     //性能等级，-2 或 0：该设备无法运行小游戏，-1：性能未知，>=1 设备性能值，该值越高，设备性能越好 (目前设备最高不到50)

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getPixelRatio() {
        return pixelRatio;
    }

    public void setPixelRatio(int pixelRatio) {
        this.pixelRatio = pixelRatio;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public int getWindowWidth() {
        return windowWidth;
    }

    public void setWindowWidth(int windowWidth) {
        this.windowWidth = windowWidth;
    }

    public int getWindowHeight() {
        return windowHeight;
    }

    public void setWindowHeight(int windowHeight) {
        this.windowHeight = windowHeight;
    }

    public int getStatusBarHeight() {
        return statusBarHeight;
    }

    public void setStatusBarHeight(int statusBarHeight) {
        this.statusBarHeight = statusBarHeight;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getWxVersion() {
        return wxVersion;
    }

    public void setWxVersion(String wxVersion) {
        this.wxVersion = wxVersion;
    }

    public String getSystemVersion() {
        return systemVersion;
    }

    public void setSystemVersion(String systemVersion) {
        this.systemVersion = systemVersion;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public int getFontSizeSetting() {
        return fontSizeSetting;
    }

    public void setFontSizeSetting(int fontSizeSetting) {
        this.fontSizeSetting = fontSizeSetting;
    }

    public String getSDKVersion() {
        return SDKVersion;
    }

    public void setSDKVersion(String SDKVersion) {
        this.SDKVersion = SDKVersion;
    }

    public int getBenchmarkLevel() {
        return benchmarkLevel;
    }

    public void setBenchmarkLevel(int benchmarkLevel) {
        this.benchmarkLevel = benchmarkLevel;
    }
}
