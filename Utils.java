package com.jin.app;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.awt.*;
import java.io.InputStream;
import java.util.Random;

/**
 * 工具类：
 * 1. 产生随机范围值（敌人出现位置，敌人移动速度，攻击力等）
 * 2. 游戏碰撞检测
 */
public class Utils {

    /**
     * 随机范围值
     * @param min 最小值（含）
     * @param max 最大值（含）
     * @return
     */
    public static int randomNum(int min,int max){
        Random r = new Random();
        return r.nextInt((max-min) + 1) + min;
    }

    /**
     * 判断两个矩形是否相交(矩形碰撞检测)
     * @param x1
     * @param y1
     * @param w1
     * @param h1
     * @param x2
     * @param y2
     * @param w2
     * @param h2
     * @return
     */
    public static boolean isHit(int x1,int y1,int w1,int h1,int x2,int y2,int w2,int h2){
        //构建矩形对象
        Rectangle r1 = new Rectangle(x1,y1,w1,h1);
        Rectangle r2 = new Rectangle(x2,y2,w2,h2);
        return r1.intersects(r2);
    }

    /**
     * 播放音效
     * @param musicName 音乐名称
     * @param isLoop    是否循环
     */
    public static void playSound(String musicName,boolean isLoop){
        new Thread(){
            @Override
            public void run() {
                do{
                    try {
                        //获取资源为一个输入流对象
                        InputStream is = Utils.class.getResourceAsStream(musicName);
                        System.out.println(is);
                        //使用输入流创建播放器对象
                        Player player = new Player(is);
                        //播放
                        player.play();
                    } catch (JavaLayerException e) {
                        throw new RuntimeException(e);
                    }
                }while(isLoop);
            }
        }.start();
    }
}
