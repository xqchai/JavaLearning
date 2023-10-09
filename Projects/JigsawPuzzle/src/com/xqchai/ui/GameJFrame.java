package com.xqchai.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class GameJFrame extends JFrame{


    //静态变量定义一个数组，表示16张图片的位置
    static int[] location = new int[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
    static ArrayList<JLabel> ImageLabel = new ArrayList<>(16);
    static int step = 0;
    static int imageCode = 1;
    static boolean isFinish = false;

    //构造方法
    public GameJFrame(){
        //初始化对话框
        initGameJFrame();
        //初始化菜单栏
        initJMenu();
        //初始化一张图片
        initImage(imageCode);
        //打乱图片
        upsetImage();

        //默认隐藏不可见，需要手动置true,放到最后写
        this.setVisible(true);
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }
            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                int index = e.getKeyCode();
                //System.out.printf("您刚刚按下的键对应的编号为%d\n",index);
                if(!isFinish){
                    //未完成的游戏才能继续操作
                    if (index == 87 || index == 38) {
                        //向上移动 W & ↑
                        moveImage(1);
                    } else if (index == 65 || index == 37) {
                        //向左移动 A & ←
                        moveImage(2);
                    } else if (index == 83 || index == 40) {
                        //向下移动 S & ↓
                        moveImage(3);
                    } else if (index == 68 || index == 39) {
                        //向右移动 D & →
                        moveImage(4);
                    } else if (index == 80 || index == 96) {
                        //作弊码P或0，直接复原图片
                        cheat();
                    }
                }
                //每次按键之后判断是否胜利

            }
        });

    }

    private void initGameJFrame(){
        JFrame gameJframe = new JFrame();

        //设置长宽，单位为像素
        this.setSize(630,800);
        //设置背景颜色
        //Frame上面有一层面板，所以给Frame设置颜色没用，需要给panel设置颜色
        this.getContentPane().setBackground(Color.white);
        //设置窗口标题
        this.setTitle("拼图游戏");
        //保持窗口在最前端
        this.setAlwaysOnTop(true);
        //保持窗口居中
        this.setLocationRelativeTo(null);
        //设置默认关闭方式，0: 1: 2: 3:关闭窗口即停止运行
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //取消默认的居中格式，才会使用xy坐标
        this.setLayout(null);

    }

    private void initJMenu(){
        //分别创建菜单栏、菜单、菜单选项对象
        JMenuBar gameJMenuBar = new JMenuBar();

        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("关于我们");

        JMenuItem replayItem = new JMenuItem("重新游戏"){};
        JMenuItem selectImage = new JMenuItem("更换图片");
        JMenuItem exitItem = new JMenuItem("退出登录");
        JMenuItem closeItem = new JMenuItem("关闭游戏");
        JMenuItem wechat = new JMenuItem("查看原图");



        replayItem.addActionListener(new ActionListener() {
            //重新开始游戏
            //为菜单栏选项绑定事件监听，因为只需要鼠标单击，所以没有用mouseListener监听
            @Override
            public void actionPerformed(ActionEvent e) {
                //打乱图片，步数清零
                upsetImage();
            }
        });

        selectImage.addActionListener(new ActionListener() {
            //更换图片
            @Override
            public void actionPerformed(ActionEvent e) {
                //每点一次，照片序号递增，换到下一张
                imageCode = imageCode%4 + 1;
                changeImage(imageCode);
            }
        });

        exitItem.addActionListener(new ActionListener() {
            //退出登录，跳转到登录界面
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginJFrame();
                //关闭本界面
                dispose();
            }
        });

        closeItem.addActionListener(new ActionListener() {
            //退出游戏
            @Override
            public void actionPerformed(ActionEvent e) {
                //0表示正常退出，非0值表示异常退出
                System.exit(0);
            }
        });

        wechat.addActionListener(new ActionListener() {
            //关于我们-公众号，弹出弹窗显示公众号图片
            //目前改为查看当前的原图
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog jDialog = new JDialog();
                jDialog.setSize(670,700);

                JLabel wechatImage = new JLabel();
                //改变图片大小
                ImageIcon imageIcon = new ImageIcon(new ImageIcon("C:\\Users\\xqchai\\Desktop\\Java\\Projects\\JigsawPuzzle\\src\\Images\\image"+imageCode+".jpg").getImage().getScaledInstance(600, 600, Image.SCALE_DEFAULT));
                wechatImage.setIcon(imageIcon);

                wechatImage.setBounds(27,25,600,600);
                jDialog.getContentPane().add(wechatImage);
                jDialog.setAlwaysOnTop(true);
                jDialog.setLocationRelativeTo(null);
                jDialog.setLayout(null);

                jDialog.setVisible(true);
            }
        });

        //把新建的对象联系起来
        gameJMenuBar.add(functionJMenu);
        gameJMenuBar.add(aboutJMenu);

        functionJMenu.add(replayItem);
        functionJMenu.add(selectImage);
        functionJMenu.add(exitItem);
        functionJMenu.add(closeItem);
        aboutJMenu.add(wechat);


        //改这个对话框设置设置好的菜单栏
        this.setJMenuBar(gameJMenuBar);
    }

    private void initImage(int imageCode){
        /*初始化图片
          根据静态的图片集合ImageLabel里面存储对象的顺序来重新加载图片
        **/

        //System.out.println("当前集合长度"+ImageLabel.size());  //test
        //循环新建各个照片对应的JLabel对象，因为变量名无法使用ij,故新建了一个ImageLabel集合来存放,已改写为静态变量

        //先移除JPane上的所有JLabel，否则会全都叠在一起
        this.getContentPane().removeAll();

        for(int i = 0; i<4; i++){
            for(int j = 0; j<4; j++){

                JLabel imageLabel = new JLabel();
                //改变图片大小 缩放
                //https://stackoverflow.com/questions/16343098/resize-a-picture-to-fit-a-jlabel/ 方法2
                ImageIcon imageIcon = new ImageIcon(new ImageIcon("C:\\Users\\xqchai\\Desktop\\Java\\Projects\\JigsawPuzzle\\src\\Images\\image"+imageCode+"\\image"+imageCode+"_"+(i*4+j+1)+".jpg").getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
                imageLabel.setIcon(imageIcon);

                ImageLabel.add(imageLabel);
                ImageLabel.get(i*4+j).setBounds(5+155*j,50 + 155*i,150,150);
                this.getContentPane().add(ImageLabel.get(i*4+j));
            }
        }

        JLabel showStep = new JLabel();
        showStep.setBounds(35,15,200,20);
        showStep.setFont(new Font("计步器",Font.BOLD,16));
        this.getContentPane().add(showStep);
        showStep.setText("已经过" + step + "步");

        //重新绘制，刷新图片，不写就不动
        repaint();

    }

    private void upsetImage(){
        /*打乱图片
          集合作为传递的参数，返回打乱顺序后的位置数组
        **/
        //定义location数组记录图片位置，默认升序为完整图片
        for (int i = 0; i < 16; i++) {
            Random r = new Random();
            //random不包括右面，取值范围0 ~ 15
            //如果把16换为ImageLabel.size()，会出现bug
            int index = r.nextInt(16);
            //System.out.printf("交换第"+ i +"张图片和"+ index + "张图片\n");   //test
            exchange(i,index);
        }
        //打乱代表重开游戏或初始化游戏，步数清零
        step = 0;
        //打乱顺序之后重新加载图片
        initImage(imageCode);
        //System.out.println("打乱完成");   //test
        //重新设置游戏状态
        isFinish = false;
    }

    private void moveImage(int direction){
        switch (direction){
            case 1 ->{
                if(findBlank()<12 && findBlank() >= 0){//空白图片不在最后一排，其他图片才能向上移动
                    //空白图片与它下面的图片互换即可
                    exchange(findBlank(),findBlank()+4);
                    step = step + 1;
                    initImage(imageCode);
                    System.out.printf("向上移动图片成功，当前为第%d次移动\n",step);
                    if(isWin()){
                        winTips();
                    }
                    log();
                }else
                    System.out.println("无效操作");
            }
            case 2 ->{
                if(findBlank()%4 != 3 && findBlank() >= 0){//空白图片不在最右，其他图片才能向左移动
                    //空白图片与它右面的图片互换即可
                    exchange(findBlank(),findBlank()+1);
                    step = step + 1;
                    initImage(imageCode);
                    System.out.printf("向左移动图片成功，当前为第%d次移动\n",step);
                    if(isWin()){
                        winTips();
                    }
                    log();
                }else
                    System.out.println("无效操作");
            }
            case 3 ->{
                if(findBlank()>3 && findBlank() >= 0){//空白图片不在第一排，其他图片才能向下移动
                    //空白图片与它上面的图片互换即可
                    exchange(findBlank(),findBlank()-4);
                    step = step + 1;
                    initImage(imageCode);
                    System.out.printf("向下移动图片成功，当前为第%d次移动\n",step);
                    if(isWin()){
                        winTips();
                    }
                    log();
                }else
                    System.out.println("无效操作");
            }
            case 4 ->{
                if(findBlank()%4 != 0 && findBlank() >= 0){//空白图片不在最左侧，其他图片才能向右移动
                    //空白图片与它左面的图片互换即可
                    exchange(findBlank(),findBlank()-1);
                    step = step + 1;
                    initImage(imageCode);
                    System.out.printf("向右移动图片成功，当前为第%d次移动\n",step);
                    if(isWin()){
                        winTips();
                    }
                    log();
                }else
                    System.out.println("无效操作");
            }
            default -> {
                System.out.println("非法参数，请使用 WASD 或 ↑↓←→ 进行游戏");
            }
        }

    }

    private int findBlank(){
        //返回空白图片location[i]=15的位置i=0-15
        for (int i = 0; i < location.length; i++) {
            if(location[i] == 15)
                return i;
        }
        System.out.println("未找到空白图片");
        return -1;
    }

    private void exchange(int i,int j){
        //交换
        JLabel temp = ImageLabel.get(i);
        ImageLabel.set(i,ImageLabel.get(j));
        ImageLabel.set(j,temp);
        //位置数组也同时交换
        int templocation = location[i];
        location[i] = location[j];
        location[j] = templocation;
    }


    private boolean isWin(){
        //判断是否胜利
        for (int i = 0; i < 16; i++) {
            if(location[i]!=i)
                return false;
        }
        return true;
    }

    private void winTips(){
        //胜利之后进行的一系列操作
        System.out.printf("你赢了！共用了%d步\n",step);
        //弹出弹窗提示胜利
        JOptionPane.showMessageDialog(this,"你赢了！共用了" + step + "步");
        //终止游戏进程
        isFinish = true;
    }

    private void log(){
        //打印一下当前的location，供参考
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.printf("%d ",location[i*4+j]);
                if(location[i*4+j]<10)//补对齐，美观
                    System.out.print(" ");
            }
            System.out.print("\n");
        }
    }

    private void cheat(){
        //作弊，直接复原图片
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++){
                if(location[j]==i)
                    exchange(i,j);
            }
        }
        //修改完图片集合，重新加载图片
        initImage(imageCode);
        System.out.println("作弊成功");
        if(isWin())
            winTips();
    }

    private void changeImage(int imageCode){

        //要先清空ImageLabel集合
        for (int i = 0; i < ImageLabel.size(); i++) {
            ImageLabel.remove(i);
            i--;
        }
        //位置数组也要重置，否则会继续使用上一张图片的位置数组
        for (int i = 0; i < 16; i++) {
            location[i] = i;
        }
        //重新加载图片
        initImage(imageCode);
        upsetImage();
    }



}
