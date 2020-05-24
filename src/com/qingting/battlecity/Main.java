package com.qingting.battlecity;

import com.qingting.battlecity.base.DirectionEnum;
import com.qingting.battlecity.base.ResourceMgr;
import com.qingting.battlecity.base.SpeedLevelEnum;
import com.qingting.battlecity.base.TankGroupEnum;
import com.qingting.battlecity.entry.Tank;
import com.qingting.battlecity.frame.BattleCityFrame;
import com.qingting.battlecity.graphics.TankGraphics;

/**
 * Created by dragonfly.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        BattleCityFrame bcf = new BattleCityFrame();

        // 出现 5 俩敌坦
        for (int i = 0; i < 5; i++) {
            int tankX = (bcf.getWidth() - ResourceMgr.tankDown.getWidth()) / 4 * i;
            TankGraphics enemyTank = new TankGraphics(new Tank(tankX, bcf.getInsets().top,
                    SpeedLevelEnum.LEVEL_THREE, DirectionEnum.DOWN, TankGroupEnum.ENEMY), bcf);
            enemyTank.setMoving(true);
            bcf.enemyTanks.add(enemyTank);
        }

        while (true) {
            Thread.sleep(50);
            bcf.repaint();
        }

    }
}
