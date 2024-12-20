package com.jin.app;


import com.jin.app.ImgUtils;
import sun.awt.image.ToolkitImage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * 玩家类
 */
public class Hero extends Sprite implements ActionListener {

    /**
     * 定时器：用于执行定时任务，例如：玩家动作，攻击，技能释放特效
     */
    private Timer timer;
    /**
     * 玩家图片索引（范围0~7）
     */
    private int index;
    /**
     * 左右，上下移动具体像素值（速度）
     */
    private int mx, my;
    /**
     * 玩家朝向“L”,"R"
     */
    private String dir;
    /**
     * 初始化玩家的血量和魔法量
     */
    private int hp = 100;
    private int mp = 100;
    /**
     * 初始化玩家的最大血量和最大魔法量
     */
    private double hp_max = 100;
    private double mp_max = 100;

    public Hero(GamePanel panel) {
        super(panel);
    }

    //返回人物朝向
    public String getDir() {
        return dir;
    }

    @Override
    public void init() {
        //设置图片
        setImg(ImgUtils.heroImages[0]);
        //设置玩家初始坐标
        setX(400);
        setY(600);
        //获取图片实际宽高
        ToolkitImage img = (ToolkitImage) getImg();
        int w = img.getWidth();
        int h = img.getHeight();
        setW(w * 3 / 2);
        setH(h * 3 / 2);

        //创建定时器对象
        timer = new Timer(100, this);
        //启动定时器
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setImg(ImgUtils.heroImages[index++]);
        if (index > 7) {
            index = 0;
        }
    }

    /**
     * 移动方法
     */
    public void move() {
        setX(getX() + mx);
        setY(getY() + my);
        //限定玩家移动边界范围(上下)
        if (getY() < Settings.GAME_HEIGHT / 2 - getH() / 2) {
            setY(Settings.GAME_HEIGHT / 2 - getH() / 2);
        }
        if (getY() > Settings.GAME_HEIGHT - getH() * 3 / 2) {
            setY(Settings.GAME_HEIGHT - getH() * 3 / 2);
        }
        //限定玩家移动范围(左右)
        //移动到最左侧时，玩家从屏幕右边出现，并且地图切换（上一张）
        if (getX() < -getW()) {
            setX(Settings.GAME_WIDTH);
            getPanel().prevMap();
        }
        //移动到最右侧时，玩家从屏幕左边出现，并地图切换（下一张）
        if (getX() > Settings.GAME_WIDTH) {
            setX(-getW());
            getPanel().nextMap();
        }
    }

    public void keyPressed(int code) {
        switch (code) {
            case KeyEvent.VK_LEFT:
                //左
                dir = "L";
                mx = -Settings.HERO_MOVE_SPEED;
                break;
            case KeyEvent.VK_UP:
                //上
                my = -Settings.HERO_MOVE_SPEED;
                break;
            case KeyEvent.VK_RIGHT:
                //右
                dir = "R";
                mx = Settings.HERO_MOVE_SPEED;
                break;
            case KeyEvent.VK_DOWN:
                //下
                my = Settings.HERO_MOVE_SPEED;
                break;
        }
    }

    public void keyReleased(int code) {
        //如果释放的是左右键，则左右移动速度归零
        if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_RIGHT) {
            mx = 0;
        }
        //如果释放的是左右键，则左右移动速度归零
        if (code == KeyEvent.VK_UP || code == KeyEvent.VK_DOWN) {
            my = 0;
        }
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
        //绘制魔法条
        g.setColor(Color.BLUE);
        g.drawRect(getX(), getY() - 10, getW(), 5);
        g.fillRect(getX(), getY() - 10, (int) (getW() * (mp / mp_max)), 5);
        //绘制血条
        g.setColor(Color.RED);
        g.drawRect(getX(), getY() - 25, getW(), 10);
        g.fillRect(getX(), getY() - 25, (int) (getW() * (hp / hp_max)), 10);
    }

    @Override
    public void destroy() {

    }

}
