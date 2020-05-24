package com.qingting.battlecity.graphics;

import com.qingting.battlecity.base.DirectionEnum;
import com.qingting.battlecity.base.ResourceMgr;
import com.qingting.battlecity.base.SpeedLevelEnum;
import com.qingting.battlecity.entry.Bullet;
import com.qingting.battlecity.entry.Tank;
import com.qingting.battlecity.frame.BattleCityFrame;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: qingting
 * @Description: 画出坦克 & 坦克动作
 * @Create: 2020/5/23 19:24
 */
public class TankGraphics {

    private Tank tank;

    private boolean moving;

    private BattleCityFrame frame;

    public TankGraphics(Tank tank, BattleCityFrame frame) {
        this.tank = tank;
        this.frame = frame;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public Tank getTank() {
        return tank;
    }

    private List<BulletGraphics> bgs = new ArrayList<>();

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹数量: " + bgs.size(), 10, 60);
        g.setColor(c);

        c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("敌人数量: " + frame.enemyTanks.size(), 10, 80);
        g.setColor(c);

        if (!tank.isLiving()) {
            frame.enemyTanks.remove(this);
        }

        // 坦克开火
        for (int i = 0; i < bgs.size(); i++) {
            // 子弹和坦克的碰撞检测
            for (int j = 0; j < frame.enemyTanks.size(); j++) {
                bgs.get(i).collideWith(frame.enemyTanks.get(j));
            }

            if (!bgs.get(i).getBullet().isLiving()) {
                bgs.remove(i);
                continue;
            }
            bgs.get(i).paint(g);
        }

        // 渲染坦克图片
        switch (tank.getDirectionEnum()) {
            case LEFT:
                g.drawImage(ResourceMgr.tankLeft, tank.getX(), tank.getY(), null);
                tank.setWidth(ResourceMgr.tankLeft.getWidth());
                tank.setHeight(ResourceMgr.tankLeft.getHeight());
                break;
            case UP:
                g.drawImage(ResourceMgr.tankUp, tank.getX(), tank.getY(), null);
                tank.setWidth(ResourceMgr.tankUp.getWidth());
                tank.setHeight(ResourceMgr.tankUp.getHeight());
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.tankRight, tank.getX(), tank.getY(), null);
                tank.setWidth(ResourceMgr.tankRight.getWidth());
                tank.setHeight(ResourceMgr.tankRight.getHeight());
                break;
            case DOWN:
                g.drawImage(ResourceMgr.tankDown, tank.getX(), tank.getY(), null);
                tank.setWidth(ResourceMgr.tankDown.getWidth());
                tank.setHeight(ResourceMgr.tankDown.getHeight());
                break;
        }

        // 坦克移动
        move();
    }

    /**
     * 改变坦克的方向
     */
    public void setTankDir(DirectionEnum dir) {
        tank.setDirectionEnum(dir);
    }

    private void move() {
        if (!moving) {
            return;
        }

        if (!checkBoundary()) {
            return;
        }

        // 移动边界检测，如果移动的速度小于或者大于边界值，靠近边境的最后一次移动刚好到达边界(降速）
        int speedLevel = tank.getSpeedLevelEnum().getLevel();
        switch (tank.getDirectionEnum()) {
            case LEFT:
                if (tank.getX() - speedLevel < 0) {
                    speedLevel = tank.getX();
                }
                tank.setX(tank.getX() - speedLevel);
                break;
            case UP:
                if (tank.getY() - speedLevel < frame.getInsets().top) {
                    speedLevel = tank.getY() - frame.getInsets().top;
                }
                tank.setY(tank.getY() - speedLevel);
                break;
            case RIGHT:
                if (tank.getX() + speedLevel > frame.getWidth() - tank.getWidth()) {
                    speedLevel = frame.getWidth() - tank.getWidth() - tank.getX();
                }
                tank.setX(tank.getX() + speedLevel);
                break;
            case DOWN:
                if (tank.getY() + speedLevel > frame.getHeight() - tank.getHeight()) {
                    speedLevel = frame.getHeight() - tank.getHeight() - tank.getY();
                }
                tank.setY(tank.getY() + speedLevel);
                break;
        }
    }

    /**
     * 边境检测
     */
    private boolean checkBoundary() {
        // 计算 X 轴是否超出边界
        if (tank.getX() < 0 || tank.getX() > frame.getWidth() - tank.getWidth()) {
            return false;
        }
        // 计算 Y 轴是否超出边界
        if (tank.getY() < 0 || tank.getY() > frame.getHeight() - tank.getHeight()) {
            return false;
        }
        return true;
    }

    /**
     * 坦克开火
     */
    public void fire() {
        // 开火前计算子弹在坦克出现的位置
        int bulletX = 0, bulletY = 0;
        switch (tank.getDirectionEnum()) {
            case LEFT:
                bulletX = tank.getX();
                bulletY = tank.getY() + ResourceMgr.tankLeft.getHeight() / 2 - 10;
                break;
            case UP:
                bulletX = tank.getX() + ResourceMgr.tankUp.getWidth() / 2 - 4;
                bulletY = tank.getY();
                break;
            case RIGHT:
                bulletX = tank.getX() + ResourceMgr.tankRight.getWidth() - ResourceMgr.bulletRight.getWidth();
                bulletY = tank.getY() + ResourceMgr.tankRight.getHeight() / 2 - 10;
                break;
            case DOWN:
                bulletX = tank.getX() + ResourceMgr.tankDown.getWidth() / 2 - 4;
                bulletY = tank.getY() + ResourceMgr.tankDown.getWidth() - ResourceMgr.bulletDown.getHeight();
                break;
        }
        Bullet bullet = new Bullet(bulletX, bulletY, SpeedLevelEnum.LEVEL_FOUR, tank.getDirectionEnum(), tank.getTankGroupEnum());
        bgs.add(new BulletGraphics(bullet, frame));
    }

    /**
     * 坦克死亡
     */
    public void die() {
        tank.setLiving(false);
    }
}
