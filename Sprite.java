package com.jin.app;

import java.awt.*;
import java.awt.image.ImageObserver;

/**
 * 游戏精灵类（玩家，敌人，道具，攻击光波）
 */
public abstract class Sprite {

    //图片元素
    private Image img;
    //坐标
    private int x,y;
    //尺寸
    private int w,h;
    //是否死亡（是否消失）
    private boolean died;
    //声明游戏面板
    private GamePanel panel;

    public Sprite(GamePanel panel){
        this.panel = panel;
        //对象构造时初始化精灵元素（设计模式之模板方法模式）
        init();
    }

    //返回面板对象
    public GamePanel getPanel(){
        return panel;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public boolean isDied() {
        return died;
    }

    public void setDied(boolean died) {
        this.died = died;
    }

    /**
     * 初始化精灵元素
     */
    public abstract void init();

    /**
     * 精灵销毁
     */
    public abstract void destroy();

    /**
     * 绘制精灵元素
     * @param g
     */
    public void draw(Graphics2D g){
        g.drawImage(img,x,y,w,h,null);
    }

}
