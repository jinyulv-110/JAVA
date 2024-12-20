package com.jin.app;


import com.jin.app.ImgUtils;
import sun.awt.image.ToolkitImage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 敌人类
 */
public class Enemy extends Sprite implements ActionListener {

    //定时器
    private Timer timer;
    //移动速度
    private int speed;
    //敌人图片索引
    private int index;
    //敌人实际血量
    private int hp = 100;
    //最大血量
    private double max_hp = 100;

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public double getMax_hp() {
        return max_hp;
    }

    public void setMax_hp(double max_hp) {
        this.max_hp = max_hp;
    }

    public Enemy(GamePanel panel) {
        super(panel);
    }

    @Override
    public void init() {
        setImg(ImgUtils.enemyImages[0]);
        ToolkitImage img = (ToolkitImage) getImg();
        int w = img.getWidth();
        int h = img.getHeight();
        //放大原始大小的1.5倍
        setW(w * 3 / 2);
        setH(h * 3 / 2);
        //初始化敌人坐标
        setX(Settings.GAME_WIDTH);
        //随机y坐标
        int min = Settings.GAME_HEIGHT / 2 - getH() / 2;
        int max = Settings.GAME_HEIGHT - getH() * 2;

        setY(Utils.randomNum(min, max));
        //随机一个移动速度
        speed = Utils.randomNum(5, 15);
        //创建并执行定时任务
        timer = new Timer(100, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //修改图片
        setImg(ImgUtils.enemyImages[++index % ImgUtils.enemyImages.length]);
        //修改敌人坐标
        setX(getX() - speed);
        if (getX() < -getW()) {
            destroy();
        }
    }

    /**
     * 重写draw方法，绘制敌人图片的同时绘制血条
     *
     * @param g
     */
    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
        //绘制血条
        //设置画笔的颜色
        g.setColor(Color.RED);
        g.drawRect(getX(), getY() - 20, getW(), 10);
        //实际血量,宽度值=（实际值/最大值）*总宽度
        g.fillRect(getX(), getY() - 20, (int) ((hp / max_hp) * getW()), 10);
    }

    @Override
    public void destroy() {
        this.setDied(true);
        //从集合中移除敌人
        getPanel().enemies.remove(this);
    }

}
