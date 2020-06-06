package com.qingting.battlecity.entry;

import com.qingting.battlecity.base.ResourceMgr;

/**
 * @Author: qingting
 * @Description: 爆炸效果
 * @Create: 2020/5/25 22:33
 */
public class Explode {

    public static int WIDTH = ResourceMgr.explodes[0].getWidth();

    public static int HEIGHT = ResourceMgr.explodes[0].getHeight();

    /**
     * X 轴位置
     */
    private int x;

    /**
     * Y 轴位置
     */
    private int y;

    public Explode(int x, int y) {
        this.x = x;
        this.y = y;
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
}
