/*
 * Created by Long Duping
 * Date 2018/12/6 14:21
 */
package cn.ccsu.common.entity;

import java.io.Serializable;

public class PositionInfo implements Serializable {

    private long latitude;              //纬度，范围为 -90~90，负数表示南纬
    private long longitude;             //经度，范围为 -180~180，负数表示西经
    private int speed;                  //速度，单位 m/s
    private int accuracy;               //位置的精确度
    private int altitude;               //高度，单位 m
    private int verticalAccuracy;       //垂直精度，单位 m（Android 无法获取，返回 0）
    private int horizontalAccuracy;     //水平精度，单位 m

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public int getAltitude() {
        return altitude;
    }

    public void setAltitude(int altitude) {
        this.altitude = altitude;
    }

    public int getVerticalAccuracy() {
        return verticalAccuracy;
    }

    public void setVerticalAccuracy(int verticalAccuracy) {
        this.verticalAccuracy = verticalAccuracy;
    }

    public int getHorizontalAccuracy() {
        return horizontalAccuracy;
    }

    public void setHorizontalAccuracy(int horizontalAccuracy) {
        this.horizontalAccuracy = horizontalAccuracy;
    }
}
