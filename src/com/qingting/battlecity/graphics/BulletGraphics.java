package com.qingting.battlecity.graphics;

import com.qingting.battlecity.base.ResourceMgr;
import com.qingting.battlecity.entry.Bullet;
import com.qingting.battlecity.frame.BattleCityFrame;

import java.awt.*;

/**
 * @Author: qingting
 * @Description: 画出子弹 & 子弹动作
 * @Create: 2020/5/23 19:25
 */
public class BulletGraphics {

    /**
     * 子弹
     */
    private Bullet bullet;

    /**
     * 主体窗口
     */
    private BattleCityFrame frame;

    /**
     * 是否存活
     */
    private boolean live = true;

    BulletGraphics(Bullet bullet, BattleCityFrame frame) {
        this.bullet = bullet;
        this.frame = frame;
    }

    boolean isLive() {
        return live;
    }

    void paint(Graphics g) {
        // 渲染子弹图片
        switch (bullet.getDirectionEnum()) {
            case LEFT:
                g.drawImage(ResourceMgr.bulletLeft, bullet.getX(), bullet.getY(), null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletUp, bullet.getX(), bullet.getY(), null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletRight, bullet.getX(), bullet.getY(), null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletDown, bullet.getX(), bullet.getY(), null);
                break;
        }
        this.move();
    }

    private void move() {
        int x = bullet.getX();
        int y = bullet.getY();
        int speed = bullet.getSpeedLevelEnum().getLevel();
        switch (bullet.getDirectionEnum()) {
            case LEFT:
                bullet.setX(x - speed);
                break;
            case UP:
                bullet.setY(y - speed);
                break;
            case RIGHT:
                bullet.setX(x + speed);
                break;
            case DOWN:
                bullet.setY(y + speed);
                break;
        }

        if (bullet.getX() < 0 || bullet.getY() < 0 || bullet.getX() > frame.getWidth() || bullet.getY() > frame.getHeight()) {
            live = false;
        }
    }
}
