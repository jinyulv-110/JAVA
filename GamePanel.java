package com.jin.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 游戏主面板：用于绘制界面中出现的任何游戏元素（玩家，道具，敌人等），等同舞台；通常情况
 * 面板是需要组装到窗体中使用的
 */
public class GamePanel extends JPanel implements ActionListener {

    /**
     * 全局变量：背景图片（地图）
     */
    private Image bgImg;
    /**
     * 声明玩家对象
     */
    private Hero hero;
    /**
     * 初始化地图索引
     */
    private int mapIndex = 0;
    /**
     * 声明集合存放篮球对象   并发编程
     */
    CopyOnWriteArrayList<Ball> balls = new CopyOnWriteArrayList<>();
    /**
     * 声明集合存敌人对象
     */
    CopyOnWriteArrayList<Enemy> enemies = new CopyOnWriteArrayList<>();
    /**
     * 定时器：定时对界面重绘
     */
    private Timer timer;

    public GamePanel() {
        //循环播放背景音乐
        Utils.playSound("/music/ikun.mp3",true);
        //设置当前面板获取焦点
        setFocusable(true);
        //为当前面板绑定按键事件
        addKeyListener(new GameKeyListener());
        //获取图片
        bgImg = ImgUtils.maps[mapIndex];
        //启用图片双缓冲
        setDoubleBuffered(true);
        //创建玩家对象并赋值
        hero = new Hero(this);
        //创建定时器对象
        timer = new Timer(10, this);
        timer.start();

        //启动敌人制造线程(内部类)
        new Thread(){
            @Override
            public void run() {
                while(true){
                    try {
                        //随机休眠1~3秒
                        sleep(Utils.randomNum(1000,3000));
                        //创建敌人对象
                        Enemy e = new Enemy(GamePanel.this);
                        //将敌人加入集合
                        enemies.add(e);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }.start();
    }

    /**
     * 切换下一张地图
     */
    public void nextMap() {
        mapIndex++;
        if (mapIndex >= ImgUtils.maps.length) {
            mapIndex = 0;
        }
        //重新加载新地图
        bgImg = ImgUtils.maps[mapIndex];
        //清除屏幕中所有敌人以及篮球
        balls.forEach(b->b.destroy());
        enemies.forEach(e->e.destroy());
    }

    /**
     * 切换上一张地图
     */
    public void prevMap() {
        mapIndex--;
        if (mapIndex < 0) {
            mapIndex = ImgUtils.maps.length - 1;
        }
        //重新加载新地图
        bgImg = ImgUtils.maps[mapIndex];
        //清除屏幕中所有敌人以及篮球
        balls.forEach(b->b.destroy());
        enemies.forEach(e->e.destroy());
    }

    /**
     * 内部类
     */
    private class GameKeyListener extends KeyAdapter {
        /**
         * 当按键被按下时触发
         *
         * @param e
         */
        @Override
        public void keyPressed(KeyEvent e) {
            //获取按键码
            int code = e.getKeyCode();
            hero.keyPressed(code);
            //判断按下的按键是否是空格
            if (code == KeyEvent.VK_SPACE) {
                //播放攻击音效
                Utils.playSound("/music/attack.mp3",false);
                //创建篮球
                Ball b = new Ball(GamePanel.this);
                b.setX(hero.getX());
                b.setY(hero.getY());
                b.setDir(hero.getDir());
                //将产生的篮球放入集合
                balls.add(b);
            }
        }

        /**
         * 当按键抬起时触发
         *
         * @param e
         */
        @Override
        public void keyReleased(KeyEvent e) {
            //获取按键码
            int code = e.getKeyCode();
            hero.keyReleased(code);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //玩家移动
        hero.move();
        //界面重绘
        repaint();
    }

    /**
     * 绘制组件到面板中
     *
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //获取画笔对象（2d）
        Graphics2D g2d = (Graphics2D) g;
        //绘制图片
        g2d.drawImage(bgImg, 0, 0, Settings.GAME_WIDTH, Settings.GAME_HEIGHT, this);
        //绘制玩家
        hero.draw(g2d);
        //绘制子弹（篮球）
        balls.forEach(b -> b.draw(g2d));
        //绘制敌人
        enemies.forEach(e -> e.draw(g2d));
    }

}
