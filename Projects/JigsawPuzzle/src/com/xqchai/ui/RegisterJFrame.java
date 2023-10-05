package com.xqchai.ui;

import javax.swing.*;
import java.awt.*;

public class RegisterJFrame extends JFrame {

    public RegisterJFrame(){

        JFrame registerJframe = new JFrame();
        //设置长宽，单位为像素
        this.setSize(600,800);
        //设置背景颜色
        this.setBackground(Color.blue);
        //设置窗口标题
        this.setTitle("超级无敌拼图游戏-注册界面");
        //保持窗口在最前端
        this.setAlwaysOnTop(true);
        //保持窗口居中
        this.setLocationRelativeTo(null);
        //设置默认关闭方式，0: 1: 2: 3:关闭窗口即停止运行
        this.setDefaultCloseOperation(3);

        //默认隐藏不可见，需要手动置true
        this.setVisible(true);
    }
    public static void main(String[] args){

    }
}
