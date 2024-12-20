package com.jin.app;

import javax.swing.*;

/**
 * 已完成功能：
 * 1. 游戏主窗体创建
 * 2. 游戏地图绘制与切换
 * 3. 精灵元素(玩家，敌人，子弹)的创建
 * 4. 玩家移动
 * 5. 敌人移动
 * 6. 玩家发射篮球移动
 * 7. 绘制血条和蓝条
 * 8. 碰撞检测（玩家攻击敌人掉血）
 * 9. 音效播放
 * 任务：
 * 1. 敌人碰撞玩家后，玩家掉血
 * 2. 敌人死亡之后随机掉落道具（鸡腿，面条）
 * 3. 玩家拾取道具加血(面条)，加蓝(鸡腿)
 * 扩展：
 * 如何实现技能释放
 */
public class Main {

    public static void main(String[] args) {
        //创建游戏窗口
        GameWindow gw = new GameWindow();
        //启动游戏
        gw.startGame();
    }
}
