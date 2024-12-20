package com.jin.app;

import java.awt.*;

/**
 * 游戏主配置接口
 */
public interface Settings {

    /**游戏标题*/
    String GAME_TITLE = "鸡哥的游戏-ikun战纪";

    /**屏幕宽度*/
    int GAME_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;

    /**屏幕高度*/
    int GAME_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;

    /**初始玩家移动速度*/
    int HERO_MOVE_SPEED = 6;

}
