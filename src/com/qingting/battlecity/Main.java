package com.qingting.battlecity;

import com.qingting.battlecity.frame.BattleCityFrame;

/**
 * Created by dragonfly.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        BattleCityFrame bcf = new BattleCityFrame();

        while (true) {
            Thread.sleep(50);
            bcf.repaint();
        }

    }
}
