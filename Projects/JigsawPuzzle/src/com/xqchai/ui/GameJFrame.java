package com.xqchai.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener {


    //静态变量定义一个数组，表示16张图片的位置
    static int[] location = new int[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
    static ArrayList<JLabel> ImageLabel = new ArrayList<>(16);
    static int step = 0;
    //构造方法
    public GameJFrame(){
        //初始化对话框
        initGameJFrame();
        //初始化菜单栏
        initJMenu();
        //初始化一张图片
        initImage();
        //打乱图片
        upsetImage();

        //默认隐藏不可见，需要手动置true,放到最后写
        this.setVisible(true);

        int key;
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
                if(index==87 || index==38){
                    //向上移动 W & ↑
                    moveImage(1);
                }else if(index==65 || index==37){
                    //向左移动 A & ←
                    moveImage(2);
                }else if(index==53 || index==40){
                    //向下移动 S & ↓
                    moveImage(3);
                }else if(index==68 || index==39){
                    //向右移动 D & →
                    moveImage(4);
                }
                //每次按键之后判断是否胜利

            }
        });

    }

    private void initGameJFrame(){
        JFrame gameJframe = new JFrame();

        //设置长宽，单位为像素
        this.setSize(600,800);
        //设置背景颜色
        this.setBackground(Color.black);
        //设置窗口标题
        this.setTitle("超级无敌拼图游戏");
        //保持窗口在最前端
        this.setAlwaysOnTop(true);
        //保持窗口居中
        this.setLocationRelativeTo(null);
        //设置默认关闭方式，0: 1: 2: 3:关闭窗口即停止运行
        this.setDefaultCloseOperation(3);
        //取消默认的居中格式，才会使用xy坐标
        this.setLayout(null);

    }

    private void initJMenu(){
        //分别创建菜单栏、菜单、菜单选项对象
        JMenuBar gameJMenuBar = new JMenuBar();

        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("关于我们");

        JMenuItem replayItem = new JMenuItem("重新游戏");
        JMenuItem exitItem = new JMenuItem("退出登录");
        JMenuItem closeItem = new JMenuItem("关闭游戏");
        JMenuItem wechat = new JMenuItem("公众号");

        //把新建的对象联系起来
        gameJMenuBar.add(functionJMenu);
        gameJMenuBar.add(aboutJMenu);

        functionJMenu.add(replayItem);
        functionJMenu.add(exitItem);
        functionJMenu.add(closeItem);
        aboutJMenu.add(wechat);

        //改这个对话框设置设置好的菜单栏
        this.setJMenuBar(gameJMenuBar);
    }

    private void initImage(){
        //待解决：相对路径是错了还是不能用？
        //JLabel imageLabel1 = new JLabel(new ImageIcon("./Images/image3.jpg"));
        //JLabel imageLabel1 = new JLabel(new ImageIcon("C:\\Users\\xqchai\\Desktop\\Java\\Projects\\JigsawPuzzle\\src\\Images\\image1.jpg"));
        //Frame上面有一层面板，所以给Frame设置颜色没用，需要给panel设置颜色
        this.getContentPane().setBackground(Color.darkGray);
        //imageLabel1.setBounds(0,0,300,300);
        //本身就有一个默认的panel面板，找到，把图片加进去
        //this.getContentPane().add(imageLabel1);

        //改写为静态变量
        //ArrayList<JLabel> ImageLabel = new ArrayList<>(16);

        //循环新建各个照片对应的JLabel对象，因为变量名无法使用ij,故新建了一个ImageLabel集合来存放
        for(int i = 0; i<4; i++){
            for(int j = 0; j<4; j++){
                JLabel imageLabel = new JLabel(new ImageIcon("C:\\Users\\xqchai\\Desktop\\Java\\Projects\\JigsawPuzzle\\src\\Images\\image1\\image1_"+(i*4+j+1)+".jpg"));
                ImageLabel.add(imageLabel);
                ImageLabel.get(i*4+j).setBounds(150*j,50 + 150*i,150,150);
                this.getContentPane().add(ImageLabel.get(i*4+j));
            }
        }
    }

    private void upsetImage(){
        /*打乱图片
          集合作为传递的参数，返回打乱顺序后的位置数组
        **/
        //定义location数组记录图片位置，默认升序为完整图片
        for (int i = 0; i < ImageLabel.size(); i++) {
            Random r = new Random();
            int index = r.nextInt(ImageLabel.size());
            exchange(i,index);
        }
        //打乱顺序之后重新放置，i代表位置
        for (int i = 0; i < ImageLabel.size(); i++) {
            //设置图片新边界
            ImageLabel.get(i).setBounds(150*(i%4),50 + 150*(i/4),150,150);
        }
    }

    private void moveImage(int direction){
        int blankKey = findBlank();
        switch (direction){
            case 1 ->{
                if(findBlank()<12 && findBlank() >= 0){//空白图片不在最后一排，其他图片才能向上移动
                    //空白图片与它下面的图片互换即可
                    exchange(findBlank(),findBlank()+4);
                    initImage();
                    System.out.println("向上移动图片成功");
                    step = step + 1;
                    if(isWin()){
                        System.out.println("You Win!!!");
                    }
                    log();
                }else
                    System.out.println("无效操作");
                break;
            }
            case 2 ->{
                if(findBlank()%4 != 3 && findBlank() >= 0){//空白图片不在最右，其他图片才能向左移动
                    //空白图片与它右面的图片互换即可
                    exchange(findBlank(),findBlank()+1);
                    initImage();
                    System.out.println("向左移动图片成功");
                    step = step + 1;
                    if(isWin()){
                        System.out.println("You Win!!!");
                    }
                    log();
                }else
                    System.out.println("无效操作");
                break;
            }
            case 3 ->{
                if(findBlank()>3 && findBlank() >= 0){//空白图片不在第一排，其他图片才能向下移动
                    //空白图片与它上面的图片互换即可
                    exchange(findBlank(),findBlank()-4);
                    initImage();
                    System.out.println("向下移动图片成功");
                    step = step + 1;
                    if(isWin()){
                        System.out.println("You Win!!!");
                    }
                    log();
                }else
                    System.out.println("无效操作");
                break;
            }
            case 4 ->{
                if(findBlank()%4 != 0 && findBlank() >= 0){//空白图片不在最左侧，其他图片才能向右移动
                    //空白图片与它左面的图片互换即可
                    exchange(findBlank(),findBlank()-1);
                    initImage();
                    System.out.println("向右移动图片成功");
                    step = step + 1;
                    if(isWin()){
                        System.out.println("You Win!!!");
                    }
                    log();
                }else
                    System.out.println("无效操作");
                break;
            }
            default -> {
                System.out.println("非法参数");
                break;
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

    private void log(){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.printf("%d ",location[i*4+j]);
                if(location[i*4+j]<10)//补对齐，美观
                    System.out.print(" ");
            }
            System.out.print("\n");
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("你按了一下键盘");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("你松开了一下键盘");
    }
}
