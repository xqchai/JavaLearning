package com.xqchai.ui;

import com.xqchai.user.User;
import com.xqchai.user.UserInformation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class RegisterJFrame extends JFrame {
    static String verificationCode1 = generateVerificationCode();

    public RegisterJFrame(){

        //初始化注册界面框体
        initRegisterJFrame();
        //初始化注册组件
        initRegisterComponents();

        //默认隐藏不可见，需要手动置true
        this.setVisible(true);
    }

    private void initRegisterJFrame(){
        /*初始化注册界面框体
        **/
        JFrame registerJframe = new JFrame();
        //设置长宽，单位为像素
        this.setSize(600,450);
        //设置背景颜色
        this.getContentPane().setBackground(Color.green);
        //设置窗口标题
        this.setTitle("用户注册");
        //保持窗口在最前端
        this.setAlwaysOnTop(true);
        //保持窗口居中
        this.setLocationRelativeTo(null);
        //设置默认关闭方式，0: 1: 2:√ 3:
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }

    private void initRegisterComponents(){

        this.getContentPane().setLayout(null);

        JLabel welcomeLabel = new JLabel("账号注册");
        welcomeLabel.setBounds(95,12,300,50);
        welcomeLabel.setFont(new Font("欢迎框",Font.BOLD,26));
        this.getContentPane().add(welcomeLabel);

        JLabel remindLabel = new JLabel();
        remindLabel.setBounds(350,20,200,50);
        remindLabel.setForeground(Color.RED);
        remindLabel.setFont(new Font("提示框",Font.ITALIC,14));
        this.getContentPane().add(remindLabel);

        //账号input
        JLabel accountTip = new JLabel("账号:");
        accountTip.setBounds(90,70,30,40);
        this.getContentPane().add(accountTip);

        JTextField accountBox = new JTextField(){};
        accountBox.setBounds(180,70,300,40);
        this.getContentPane().add(accountBox);

        //密码input
        JLabel passwordTip = new JLabel("密码:");
        passwordTip.setBounds(90,120,30,40);
        this.getContentPane().add(passwordTip);

        JTextField passwordBox = new JTextField();
        passwordBox.setBounds(180,120,300,40);
        this.getContentPane().add(passwordBox);

        //二次密码input
        JLabel passwordTip2 = new JLabel("再次输入：");
        passwordTip2.setBounds(90,170,80,40);
        this.getContentPane().add(passwordTip2);

        JTextField passwordBox2 = new JTextField();
        passwordBox2.setBounds(180,170,300,40);
        this.getContentPane().add(passwordBox2);

        //手机号input
        JLabel phoneNumberTip = new JLabel("手机号码：");
        phoneNumberTip.setBounds(90,220,80,40);
        this.getContentPane().add(phoneNumberTip);

        JTextField phoneNumberBox = new JTextField();
        phoneNumberBox.setBounds(180,220,300,40);
        this.getContentPane().add(phoneNumberBox);

        //验证码
        JLabel verificationCodeTip = new JLabel("验证码：");
        verificationCodeTip.setBounds(90,270,80,40);
        this.getContentPane().add(verificationCodeTip);

        JTextField verificationCodeBox = new JTextField();
        verificationCodeBox.setBounds(180,270,200,40);
        this.getContentPane().add(verificationCodeBox);

        JLabel verificationLabel = new JLabel();
        verificationLabel.setBounds(400,270,150,40);
        verificationLabel.setFont(new Font("验证码",Font.BOLD,25));
        //每次生成随机四位验证码
        verificationLabel.setText(verificationCode1);
        this.getContentPane().add(verificationLabel);
        verificationLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //鼠标点击
                verificationCode1 = generateVerificationCode();
                verificationLabel.setText(verificationCode1);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        //勾选框，需要同意协议
        JCheckBox agree = new JCheckBox("  我已阅读并同意游戏服务协议与隐私保护指引  ");
        agree.setBounds(95,315,300,25);
        //框体设置透明
        agree.setOpaque(false);
        this.getContentPane().add(agree);

        //注册按钮
        JButton registerButton = new JButton("注册");
        registerButton.setBounds(140,350,300,40);
        this.getContentPane().add(registerButton);
        registerButton.addActionListener(new ActionListener() {
            //为登录按钮绑定监听事件
            @Override
            public void actionPerformed(ActionEvent e) {

                //注册标志位，默认为1，可以注册
                int registerFlag = 1;

                //获取文字框内容
                String account = accountBox.getText();
                String password = passwordBox.getText();
                String password2 = passwordBox2.getText();
                String phoneNumber = phoneNumberBox.getText();
                String verificationCode = verificationCodeBox.getText();
                boolean isAgree = !agree.isSelected();


                if(account.isEmpty())
                    remindLabel.setText("账号不能为空");

                else if (userCheck(account,UserInformation.userInformation)>-1)
                    remindLabel.setText("该账号名已存在");

                else if (password.isEmpty())
                    remindLabel.setText("密码不能为空");

                else if (password2.isEmpty()) {
                    remindLabel.setText("请再次输入您的密码");
                }

                else if(!password.equals(password2)) {
                    remindLabel.setText("两次输入的密码不一致");
                    passwordBox2.setText("");
                }

                else if (phoneNumber.isEmpty())
                    remindLabel.setText("手机号码不能为空");

                else if(!phoneNumberVerify(phoneNumber))
                    remindLabel.setText("您的手机号输入有误");

                else if (verificationCode.isEmpty()||(!verificationCode.equals(verificationCode1))){
                    remindLabel.setText("验证码输入有误");
                    //清空输入
                    verificationCodeBox.setText("");
                    //System.out.println(verificationCode+"   "+verificationCode1);    //test
                    //更换验证码
                    verificationCode1 = generateVerificationCode();
                    verificationLabel.setText(verificationCode1);
                }

                else if (isAgree)
                    remindLabel.setText("请先同意游戏服务协议");

                else{
                    //注册新用户
                    User newUser = new User();


                    newUser.setAccount(account);
                    newUser.setPassword(password);
                    newUser.setPhoneNumber(phoneNumber);

                    UserInformation.userInformation.add(newUser);

                    JOptionPane.showMessageDialog(passwordBox2,"注册成功");
                    //注册成功则关闭本界面
                    dispose();

                }
            }
        });

    }




    public static int userCheck(String userName, ArrayList<User> userInformation){
        //根据用户名进行用户查询，返回-1查询失败，查询成功返回列表序号i
        for (int i = 0; i < userInformation.size(); i++) {
            if(userInformation.get(i).getAccount().equals(userName)){
                //System.out.println("已查询到该用户信息");
                return i;
            }
        }
        return -1;
    }

    public static boolean phoneNumberVerify(String phoneNumber){
        //手机号验证，返回true
        if(phoneNumber.length()!=11 || phoneNumber.charAt(0) !='1')
            return false;
        for (int i = 0; i < phoneNumber.length(); i++) {
            if(phoneNumber.charAt(i)<'0' || phoneNumber.charAt(i)>'9')
                return false;
        }
        return true;
    }

    public static String generateVerificationCode(){

        char[] cerificationCode = new char[4];


        for (int i = 0; i < 4; i++) {

            Random r = new Random();
            int randomNumber1 = r.nextInt(3);
            int randomNumber2 = r.nextInt(10);
            int randomNumber3 = r.nextInt(26);

            //四位验证码，随机数字，小写字母，大写字母
            switch(randomNumber1){
                case 0->{
                    //随机数字，ASCII 48-57
                    cerificationCode[i] = (char) (48 + randomNumber2);
                    break;
                }
                case 1->{
                    //随机小写字母，ASCII 97-122
                    cerificationCode[i] = (char) (97 + randomNumber3);
                    break;
                }
                case 2->{
                    //随机大写字母，ASCII 65-90
                    cerificationCode[i] = (char) (65 + randomNumber3);
                    break;
                }
                default -> {
                    break;
                }
            }

        }


        return new String(cerificationCode);
    }

}
