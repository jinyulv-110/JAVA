package com.jin.app;

import com.jin.app.ImgUtils;
import sun.awt.image.ToolkitImage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * 篮球类：有玩家发射
 */
public class Ball extends Sprite implements ActionListener {

    //初始化篮球朝向  R-右  L-左
    private String dir = "R";
    private Timer timer;
    //初始化篮球飞行速度
    private int speed = 20;

    public Ball(GamePanel panel) {
        super(panel);
    }

    //设置球的朝向
    public void setDir(String dir) {
        this.dir = dir;
    }

    @Override
    public void init() {
        //初始化篮球图片
        setImg(ImgUtils.bullet);

        //获取图片实际宽高
        ToolkitImage img = (ToolkitImage) getImg();
        int w = img.getWidth();
        int h = img.getHeight();
        setW(w * 3 / 2);
        setH(h * 3 / 2);

        timer = new Timer(10, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //根据球的朝向改变坐标（飞行）
        if (Objects.equals(dir, "R")) {
            setX(getX() + speed);
        }
        if(Objects.equals(dir, "L")){
            setX(getX() - speed);
        }
        //当篮球飞出屏幕外时，清理
        if(getX() < -getW() || getX() > Settings.GAME_WIDTH){
            destroy();
        }
        //循环遍历每一个敌人
        for(Enemy em: getPanel().enemies){
            //判断球是否和敌人碰撞
            if(!isDied() && Utils.isHit(getX(),getY(),getW(),getH(),em.getX(),em.getY(),em.getW(),em.getH())){
                //销毁当前篮球
                destroy();
                //减少敌人血量
                em.setHp(em.getHp() - Utils.randomNum(10,20));
                if(em.getHp() <= 0){
                    em.destroy();
                }
            }
        }
    }

    @Override
    public void destroy() {
        //标记篮球销毁
        this.setDied(true);
        //将当前球从集合中移除
        getPanel().balls.remove(this);
    }

}
