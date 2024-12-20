package com.jin.app;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 游戏窗体类（游戏面板的主要容器）
 * 继承：子类继承父类之后，父类中的非私有元素子类都可以直接使用，同时还能扩展
 */
public class GameWindow extends JFrame {

    /**
     * 构造器：构造方法，构造函数，在创建对象时执行一些初始化的操作
     */
    public GameWindow() {
        //设置游戏标题
        setTitle(Settings.GAME_TITLE);
        //设置窗口大小(全屏)
        setSize(Settings.GAME_WIDTH,Settings.GAME_HEIGHT);
        //设置窗口默认关闭按钮不做任何处理
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        //为关闭按钮绑定事件(设计模式之一-适配器模式)   封装，继承，多态
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //弹出提示框，提示关闭信息
                int i = JOptionPane.showConfirmDialog(GameWindow.this,"骚年，确定不玩了吗？","退出游戏",JOptionPane.YES_NO_OPTION);
                if(i == 0){
                    //系统退出
                    System.exit(0);
                }
            }
        });
        //将面板加入窗体中展示
        add(new GamePanel());
    }

    public void startGame(){
        //显示窗口
        setVisible(true);
    }

}
