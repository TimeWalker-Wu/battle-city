package com.qingting.battlecity.frame;

import com.qingting.battlecity.base.DirectionEnum;
import com.qingting.battlecity.base.SpeedLevelEnum;
import com.qingting.battlecity.entry.Tank;
import com.qingting.battlecity.graphics.TankGraphics;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @Author: qingting
 * @Description: 坦克大战主体窗口
 * @Create: 2020/5/15 16:05
 */
public class BattleCityFrame extends Frame {

    private static final int GAME_WIDTH = 800;
    private static final int GAME_HEIGHT = 600;

    private TankGraphics tankGraphics = new TankGraphics(new Tank(200, 200, SpeedLevelEnum.LEVEL_THREE, DirectionEnum.DOWN), this);

    public BattleCityFrame() {
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
        this.setResizable(false);
        this.setTitle("坦克大战 - 定制版");
        this.setVisible(true);

        this.addKeyListener(new MyKeyListener());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    private Image offScreenImage = null;
    @Override
    public void update(Graphics g) {
        if(offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        tankGraphics.paint(g);
    }

    class MyKeyListener extends KeyAdapter {

        private boolean vL = false;
        private boolean vU = false;
        private boolean vR = false;
        private boolean vD = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT :
                    vL = true;
                    break;
                case KeyEvent.VK_UP :
                    vU = true;
                    break;
                case KeyEvent.VK_RIGHT :
                    vR = true;
                    break;
                case KeyEvent.VK_DOWN :
                    vD = true;
                    break;
                case KeyEvent.VK_SPACE :
                    tankGraphics.fire();
                    break;
                default :
                        break;
            }

            setMainTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT :
                    vL = false;
                    break;
                case KeyEvent.VK_UP :
                    vU = false;
                    break;
                case KeyEvent.VK_RIGHT :
                    vR = false;
                    break;
                case KeyEvent.VK_DOWN :
                    vD = false;
                    break;
                default :
                    break;
            }

            setMainTankDir();
        }

        private void setMainTankDir() {
            if (!vL && !vU && !vR && !vD) {
                tankGraphics.setMoving(false);
            } else {
                tankGraphics.setMoving(true);
                if (vL) {
                    tankGraphics.setTankDir(DirectionEnum.LEFT);
                }
                if (vU) {
                    tankGraphics.setTankDir(DirectionEnum.UP);
                }
                if (vR) {
                    tankGraphics.setTankDir(DirectionEnum.RIGHT);
                }
                if (vD) {
                    tankGraphics.setTankDir(DirectionEnum.DOWN);
                }
            }
        }
    }

}
