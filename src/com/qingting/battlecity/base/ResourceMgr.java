package com.qingting.battlecity.base;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Author: qingting
 * @Description: 资源文件加载
 * @Create: 2020/5/24 12:39
 */
public class ResourceMgr {

    private ResourceMgr() {

    }

    // 坦克资源图片
    public static BufferedImage tankLeft, tankUp, tankRight, tankDown;

    // 子弹资源图片
    public static BufferedImage bulletLeft, bulletUp, bulletRight, bulletDown;

    // 爆炸资源图片
    public static BufferedImage[] explodes = new BufferedImage[16];

    static {
        try {
            tankLeft = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("resources/images/tank_init_left.png"));
            tankUp = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("resources/images/tank_init_up.png"));
            tankRight = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("resources/images/tank_init_right.png"));
            tankDown = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("resources/images/tank_init_down.png"));

            bulletLeft = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("resources/images/bullet_init_left.png"));
            bulletUp = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("resources/images/bullet_init_up.png"));
            bulletRight = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("resources/images/bullet_init_right.png"));
            bulletDown = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("resources/images/bullet_init_down.png"));

            for (int i = 0; i < 16; i++) {
                explodes[i] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("resources/images/e" + (i + 1) + ".gif"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
