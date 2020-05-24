package com.qingting.battlecity.graphics;

import com.qingting.battlecity.base.ResourceMgr;
import com.qingting.battlecity.entry.Bullet;
import com.qingting.battlecity.entry.Tank;
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

    BulletGraphics(Bullet bullet, BattleCityFrame frame) {
        this.bullet = bullet;
        this.frame = frame;
    }

    public Bullet getBullet() {
        return bullet;
    }

    void paint(Graphics g) {
        // 渲染子弹图片
        switch (bullet.getDirectionEnum()) {
            case LEFT:
                g.drawImage(ResourceMgr.bulletLeft, bullet.getX(), bullet.getY(), null);
                bullet.setWidth(ResourceMgr.bulletLeft.getWidth());
                bullet.setHeight(ResourceMgr.bulletLeft.getHeight());
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletUp, bullet.getX(), bullet.getY(), null);
                bullet.setWidth(ResourceMgr.bulletUp.getWidth());
                bullet.setHeight(ResourceMgr.bulletUp.getHeight());
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletRight, bullet.getX(), bullet.getY(), null);
                bullet.setWidth(ResourceMgr.bulletRight.getWidth());
                bullet.setHeight(ResourceMgr.bulletRight.getHeight());
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletDown, bullet.getX(), bullet.getY(), null);
                bullet.setWidth(ResourceMgr.bulletRight.getWidth());
                bullet.setHeight(ResourceMgr.bulletRight.getHeight());
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
            bullet.setLiving(false);
        }
    }

    /**
     * 子弹和坦克的碰撞
     */
    public void collideWith(TankGraphics tankGraphics) {
        if (bullet.getTankGroupEnum() == tankGraphics.getTank().getTankGroupEnum()) {
            return;
        }
        Rectangle bulletRec = new Rectangle(bullet.getX(), bullet.getY(), bullet.getWidth(), bullet.getHeight());
        Rectangle tankRec = new Rectangle(tankGraphics.getTank().getX(), tankGraphics.getTank().getY(), tankGraphics.getTank().getWidth(), tankGraphics.getTank().getHeight());
        if (bulletRec.intersects(tankRec)) {
            tankGraphics.die();
            this.die();
        }
    }

    /**
     * 子弹死亡
     */
    private void die() {
        bullet.setLiving(false);
    }
}
