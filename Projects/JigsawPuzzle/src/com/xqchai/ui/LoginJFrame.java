package com.xqchai.ui;

import com.xqchai.user.UserInformation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicBoolean;

public class LoginJFrame extends JFrame {

    public LoginJFrame(){

        //初始化登录界面框体
        initLoginJFrame();
        //初始化组件
        initLoginComponents();


        //默认隐藏不可见，需要手动置true
        this.setVisible(true);

    }

    private void initLoginJFrame(){
        /*初始化框体
        **/

        JFrame loginJframe = new JFrame();
        //设置长宽，单位为像素
        this.setSize(450,350);
        //设置背景颜色
        this.getContentPane().setBackground(Color.yellow);
        //设置窗口标题
        this.setTitle("登录");
        //保持窗口在最前端
        this.setAlwaysOnTop(true);
        //保持窗口居中
        this.setLocationRelativeTo(null);
        //设置默认关闭方式，只关闭当前窗口
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void initLoginComponents(){
        /*初始化登录界面的各个组件
          包括账号栏和密码栏，登录俺按钮和注册按钮
        **/

        //这句必须要写！
        //JFrame的默认布局是BorderLayout边框布局.要改为null
        this.getContentPane().setLayout(null);

        //设置背景颜色
//        JLabel backgroundLabel = new JLabel(new ImageIcon("C:\\Users\\xqchai\\Desktop\\Java\\Projects\\JigsawPuzzle\\src\\Images\\background1.jpg"));
//        backgroundLabel.setBounds(0,0,this.getWidth(),this.getHeight());
//        this.getContentPane().add(backgroundLabel);

        JLabel welcomeLabel = new JLabel("欢迎来到拼图游戏");
        welcomeLabel.setBounds(95,20,300,50);
        welcomeLabel.setFont(new Font("默认",Font.BOLD,30));
        this.getContentPane().add(welcomeLabel);


        //账号框体
        JLabel accountTip = new JLabel("账号:");
        accountTip.setBounds(90,90,30,40);
        this.getContentPane().add(accountTip);

        JLabel passwordTip = new JLabel("密码:");
        passwordTip.setBounds(90,140,30,40);
        this.getContentPane().add(passwordTip);

        JTextField accountBox = new JTextField(){};
        accountBox.setBounds(140,90,200,40);
        this.getContentPane().add(accountBox);

//        JTextField passwordBox = new JTextField();
//        passwordBox.setBounds(140,140,200,40);
//        this.getContentPane().add(passwordBox);
        JPasswordField passwordBox = new JPasswordField();
        passwordBox.setBounds(140,140,200,40);
        //默认设置密码隐藏
        passwordBox.setEchoChar('*');
        AtomicBoolean isHide = new AtomicBoolean(true);
        this.getContentPane().add(passwordBox);


        JButton hideJButton = new JButton();
        ImageIcon imageIcon0 = new ImageIcon(new ImageIcon("C:\\Users\\xqchai\\Desktop\\Java\\Projects\\JigsawPuzzle\\src\\Images\\eye_0.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        ImageIcon imageIcon1 = new ImageIcon(new ImageIcon("C:\\Users\\xqchai\\Desktop\\Java\\Projects\\JigsawPuzzle\\src\\Images\\eye_1.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        hideJButton.setBounds(350,145,30,30);
        hideJButton.setIcon(imageIcon1);
        hideJButton.addActionListener(e -> {
            if(isHide.get()){
                //设置密码取消隐藏
                passwordBox.setEchoChar((char) 0);
                //更换图标
                hideJButton.setIcon(imageIcon0);
                isHide.set(false);
            }else{
                //设置密码隐藏
                passwordBox.setEchoChar('*');
                //更换图标
                hideJButton.setIcon(imageIcon1);
                isHide.set(true);

            }

        });
        this.getContentPane().add(hideJButton);

        JLabel remindLabel = new JLabel();
        remindLabel.setBounds(250,182,200,20);
        remindLabel.setForeground(Color.RED);
        remindLabel.setFont(new Font("提示框",Font.ITALIC,14));
        this.getContentPane().add(remindLabel);

        JButton loginButton = new JButton("登录");
        loginButton.setBounds(115,210,80,30);
        this.getContentPane().add(loginButton);


        loginButton.addActionListener(new ActionListener() {
            //为登录按钮绑定监听事件
            @Override
            public void actionPerformed(ActionEvent e) {

                String account = accountBox.getText();
                String password = passwordBox.getText();

                if(account.isEmpty())
                    remindLabel.setText("账号不能为空");
                else if (password.isEmpty())
                    remindLabel.setText("密码不能为空");
                else if(isAgreeLogin(account,password) == -1)
                    remindLabel.setText("该用户不存在");
                else if(isAgreeLogin(account,password) == 0)
                    remindLabel.setText("密码输入错误");
                else if(isAgreeLogin(account,password) == 1){
                    //登录成功跳转至游戏界面
                    new GameJFrame();
                    //关闭本界面
                    dispose();
                }
            }
        });

        //注册按钮，点击跳转至注册界面
        JButton registerButton = new JButton("注册");
        registerButton.setBounds(235,210,80,30);
        this.getContentPane().add(registerButton);
        registerButton.addActionListener(e -> {
            //另一种写法，为注册按钮绑定监听
            new RegisterJFrame();
        });

    }

    public int isAgreeLogin(String account,String password){
        //检查账号是否存在，账号密码是否正确
        //1为均正确，0为密码错误，-1为用户不存在
        if(RegisterJFrame.userCheck(account,UserInformation.userInformation)>-1){

            if(UserInformation.userInformation.get(RegisterJFrame.userCheck(account,UserInformation.userInformation)).getPassword().equals(password)){
                return 1;
            }else
                return 0;
        }
        return -1;
    }

}
