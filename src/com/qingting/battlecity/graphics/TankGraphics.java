package com.qingting.battlecity.graphics;

import com.qingting.battlecity.base.*;
import com.qingting.battlecity.entry.Bullet;
import com.qingting.battlecity.entry.Explode;
import com.qingting.battlecity.entry.Tank;
import com.qingting.battlecity.frame.BattleCityFrame;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Author: qingting
 * @Description: 画出坦克 & 坦克动作
 * @Create: 2020/5/23 19:24
 */
public class TankGraphics {

    private Tank tank;

    private boolean moving;

    private BattleCityFrame frame;

    private Random random = new Random();

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

        // 防止敌坦智能移动到边界时在边界停留时间过长，判断当敌坦到边界后立即更换方向
        boolean isEnemyBoundary = false;

       // 移动并加入边界检测
        int speedLevel = tank.getSpeedLevelEnum().getLevel();
        switch (tank.getDirectionEnum()) {
            case LEFT:
                if (tank.getX() - speedLevel < 0) {
                    tank.setX(0);
                    isEnemyBoundary = true;
                } else {
                    tank.setX(tank.getX() - speedLevel);
                }
                break;
            case UP:
                if (tank.getY() - speedLevel < frame.getInsets().top) {
                    isEnemyBoundary = true;
                    tank.setY(frame.getInsets().top);
                } else {
                    tank.setY(tank.getY() - speedLevel);
                }
                break;
            case RIGHT:
                if (tank.getX() + speedLevel > frame.getWidth() - tank.getWidth()) {
                    isEnemyBoundary = true;
                    tank.setX(frame.getWidth() - tank.getWidth());
                } else {
                    tank.setX(tank.getX() + speedLevel);
                }
                break;
            case DOWN:
                if (tank.getY() + speedLevel > frame.getHeight() - tank.getHeight()) {
                    isEnemyBoundary = true;
                    tank.setY(frame.getHeight() - tank.getHeight());
                } else {
                    tank.setY(tank.getY() + speedLevel);
                }
                break;
        }
        if (isEnemyBoundary) {
            tank.setDirectionEnum(DirectionEnum.values()[random.nextInt(4)]);
        }
    }

    /**
     * 坦克开火
     */
    public void fire() {
        if(tank.getTankGroupEnum() == TankGroupEnum.FRIEND) new Thread(()->new Audio("resources/audio/tank_fire.wav").play()).start();
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
        int eX = tank.getX() + tank.getWidth() / 2 - Explode.WIDTH / 2;
        int eY = tank.getY() + tank.getHeight() / 2 - Explode.HEIGHT / 2;
        frame.explodes.add(new ExplodeGraphics(new Explode(eX, eY), frame));
    }
}
