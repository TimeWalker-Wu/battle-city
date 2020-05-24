package com.qingting.battlecity.entry;

import com.qingting.battlecity.base.DirectionEnum;
import com.qingting.battlecity.base.SpeedLevelEnum;

/**
 * @Author: qingting
 * @Description: 子弹
 * @Create: 2020/5/23 19:01
 */
public class Bullet {

    /**
     * X 轴位置
     */
    private int x;

    /**
     * Y 轴位置
     */
    private int y;

    /**
     * 速度等级
     */
    private SpeedLevelEnum speedLevelEnum;

    /**
     * 方向
     */
    private DirectionEnum directionEnum;

    public Bullet(int x, int y, SpeedLevelEnum speedLevelEnum, DirectionEnum directionEnum) {
        this.x = x;
        this.y = y;
        this.speedLevelEnum = speedLevelEnum;
        this.directionEnum = directionEnum;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public SpeedLevelEnum getSpeedLevelEnum() {
        return speedLevelEnum;
    }

    public void setSpeedLevelEnum(SpeedLevelEnum speedLevelEnum) {
        this.speedLevelEnum = speedLevelEnum;
    }

    public DirectionEnum getDirectionEnum() {
        return directionEnum;
    }

    public void setDirectionEnum(DirectionEnum directionEnum) {
        this.directionEnum = directionEnum;
    }
}
