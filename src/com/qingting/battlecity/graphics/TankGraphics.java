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

    private List<BulletGraphics> bgs = new ArrayList<>();

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹数量: " + bgs.size(), 10, 60);
        g.setColor(c);

        // 坦克开火
        for (int i = 0; i < bgs.size(); i++) {
            if (!bgs.get(i).isLive()) {
                bgs.remove(i);
                continue;
            }
            bgs.get(i).paint(g);
        }

        // 渲染坦克图片
        switch (tank.getDirectionEnum()) {
            case LEFT:
                g.drawImage(ResourceMgr.tankLeft, tank.getX(), tank.getY(), null);
                break;
            case UP:
                g.drawImage(ResourceMgr.tankUp, tank.getX(), tank.getY(), null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.tankRight, tank.getX(), tank.getY(), null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.tankDown, tank.getX(), tank.getY(), null);
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
        int x = tank.getX();
        int y = tank.getY();
        int speed = tank.getSpeedLevelEnum().getLevel();
        switch (tank.getDirectionEnum()) {
            case LEFT:
                tank.setX(x - speed);
                break;
            case UP:
                tank.setY(y - speed);
                break;
            case RIGHT:
                tank.setX(x + speed);
                break;
            case DOWN:
                tank.setY(y + speed);
                break;
        }
    }

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
        Bullet bullet = new Bullet(bulletX, bulletY, SpeedLevelEnum.LEVEL_FOUR, tank.getDirectionEnum());
        bgs.add(new BulletGraphics(bullet, frame));
    }
}
