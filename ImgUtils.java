package com.jin.app;

import java.awt.*;
import java.net.URL;

/**
 * @Author xiangge
 * @Date 2024/12/12 16:48
 * @Description  图片工具类
 */
public class ImgUtils {

    /**
     * 地图图片数组
     */
    public static Image[] maps = {
            getImage("img/bg0.jpg"),
            getImage("img/bg1.jpg"),
            getImage("img/bg2.jpg"),
            getImage("img/bg3.jpg"),
            getImage("img/bg4.png"),
            getImage("img/bg5.png"),
            getImage("img/bg6.png"),
            getImage("img/bg7.png"),
            getImage("img/bg8.png")
    };

    /**
     * 根据路径获取图片
     * @param path 图片路径
     * @return 图片对象
     */
    private static Image getImage(String path) {
        URL url = ImgUtils.class.getClassLoader().getResource(path);
        if (url == null) {
            throw new RuntimeException("无法找到资源: " + path);
        }
        return Toolkit.getDefaultToolkit().getImage(url);
    }


    /**玩家图片*/
    public static Image[] heroImages={
            getImage("img/ikun/0.png"),
            getImage("img/ikun/4.png"),
            getImage("img/ikun/1.png"),
            getImage("img/ikun/5.png"),
            getImage("img/ikun/2.png"),
            getImage("img/ikun/6.png"),
            getImage("img/ikun/3.png"),
            getImage("img/ikun/7.png")
    };

    /**篮球（子弹）图片*/
    public static Image bullet = getImage("img/ikun/ball.png");

    /**敌人图片*/
    public static Image[] enemyImages = {
            getImage("img/enemy/1.png"),
            getImage("img/enemy/2.png"),
            getImage("img/enemy/3.png"),
            getImage("img/enemy/4.png"),
            getImage("img/enemy/5.png"),
            getImage("img/enemy/6.png"),
            getImage("img/enemy/7.png"),
            getImage("img/enemy/8.png"),
            getImage("img/enemy/9.png"),
            getImage("img/enemy/10.png"),
            getImage("img/enemy/11.png"),
            getImage("img/enemy/12.png"),
            getImage("img/enemy/13.png"),
            getImage("img/enemy/14.png"),
            getImage("img/enemy/15.png"),
            getImage("img/enemy/16.png"),
    };
}
