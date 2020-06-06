package com.qingting.battlecity.graphics;

import com.qingting.battlecity.base.Audio;
import com.qingting.battlecity.base.ResourceMgr;
import com.qingting.battlecity.entry.Explode;
import com.qingting.battlecity.frame.BattleCityFrame;

import java.awt.*;

/**
 * @Author: qingting
 * @Description: 画出爆炸效果
 * @Create: 2020/5/25 22:40
 */
public class ExplodeGraphics {

    /**
     * 爆炸效果
     */
    private Explode explode;

    /**
     * 主体窗口
     */
    private BattleCityFrame frame;

    public ExplodeGraphics(Explode explode, BattleCityFrame frame) {
        this.explode = explode;
        this.frame = frame;

        new Thread(()->new Audio("resources/audio/explode.wav").play()).start();
    }

    private int step = 0;


    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.explodes[step++], explode.getX(), explode.getY(), null);

        if (step >= ResourceMgr.explodes.length) {
            frame.explodes.remove(this);
        }
    }
}
