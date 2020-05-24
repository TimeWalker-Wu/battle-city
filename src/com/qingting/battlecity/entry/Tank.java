package com.qingting.battlecity.entry;

import com.qingting.battlecity.base.DirectionEnum;
import com.qingting.battlecity.base.SpeedLevelEnum;
import com.qingting.battlecity.base.TankGroupEnum;

/**
 * @Author: qingting
 * @Description: 坦克
 * @Create: 2020/5/23 19:01
 */
public class Tank {

    /**
     * X 轴位置
     */
    private int x;

    /**
     * Y 轴位置
     */
    private int y;

    /**
     * 子弹宽度
     */
    private int width;

    /**
     * 子弹高度
     */
    private int height;

    /**
     * 速度
     */
    private SpeedLevelEnum speedLevelEnum;

    /**
     * 方向
     */
    private DirectionEnum directionEnum;

    /**
     * 分组：友军坦克、敌军坦克
     */
    private TankGroupEnum tankGroupEnum;

    /**
     * 是否存活
     */
    private boolean living = true;

    public Tank(int x, int y, SpeedLevelEnum speedLevelEnum, DirectionEnum directionEnum,TankGroupEnum tankGroupEnum) {
        this.x = x;
        this.y = y;
        this.speedLevelEnum = speedLevelEnum;
        this.directionEnum = directionEnum;
        this.tankGroupEnum = tankGroupEnum;
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isLiving() {
        return living;
    }

    public void setLiving(boolean living) {
        this.living = living;
    }

    public TankGroupEnum getTankGroupEnum() {
        return tankGroupEnum;
    }

    public void setTankGroupEnum(TankGroupEnum tankGroupEnum) {
        this.tankGroupEnum = tankGroupEnum;
    }
}
