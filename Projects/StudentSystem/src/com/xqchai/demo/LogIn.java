package com.xqchai.demo;

import java.util.ArrayList;
import java.util.Scanner;

//登录界面
public class LogIn {

    public static void register(ArrayList<User> userInformation){
        //注册新用户,注意验证，用户名不能重复，电话号和身份证号需要符合规范，另写验证方法
        User newUser = new User();

        System.out.println("开始注册新用户");
        System.out.println("请输入您的用户名");
        String username = enter();
        while(userCheck(username,userInformation)>-1){
            System.out.println("该用户名已存在，请重新输入：");
            username = enter();
        }
        newUser.setUserName(username);

        int passwordFlag = 0;
        while(passwordFlag==0){
            System.out.println("请输入您的密码");
            String password = enter();
            System.out.println("请再次输入您的密码");
            String password2 = enter();
            if(password.equals(password2)){
                newUser.setPassword(password);
                passwordFlag = 1;
            }
            else
                System.out.println("两次密码输入不一致，请重新输入：");
        }

        System.out.println("请输入您的手机号");
        String phonenumber = enter();
        while(!phoneNumberVerify(phonenumber)){
            System.out.println("手机号码输入有误，请重新输入：");
            phonenumber = enter();
        }
        newUser.setPhoneNumber(phonenumber);

        System.out.println("请输入您的身份证号");
        String idnumber = enter();
        while(!idNumberVerify(idnumber)){
            System.out.println("身份证号码输入有误，请重新输入：");
            idnumber = enter();
        }
        newUser.setIdNumber(idnumber);

        userInformation.add(newUser);
        System.out.println("注册成功");

    }

    public static int login(ArrayList<User> userInformation){
        //登录
        System.out.println("开始登录");
        System.out.println("请输入您的用户名");
        String username = enter();
        if(userCheck(username,userInformation)>-1){
            System.out.println("请输入您的密码");
            String password = enter();
            if(userInformation.get(userCheck(username,userInformation)).getPassword().equals(password)){
                System.out.println("登录成功");
                return 0;
            }else
                System.out.println("密码错误！");
        }else
            System.out.println("该用户不存在！");
        return 1;
    }

    public static void forgetPassword(ArrayList<User> userInformation){
        //输入用户名且存在后，先验证身份证号或手机号（验证码），正确后可以开始修改密码
        System.out.println("开始修改密码：");
        System.out.println("请输入您的用户名：");
        String username = enter();
        if(userCheck(username,userInformation)>-1){
            //此处应该先验证身份
            System.out.println("请输入您的新密码");
            String password = enter();
            //修改密码
            userInformation.get(userCheck(username,userInformation)).setPassword(password);
            System.out.println("修改密码成功");
        }else
            System.out.println("该用户不存在！");
    }

    public static String enter(){
        //从键盘输入
        Scanner sc = new Scanner(System.in);
        //String text = sc.nextLine();
        return sc.nextLine();
    }

    public static int userCheck(String userName, ArrayList<User> userInformation){
        //根据用户名进行用户查询，返回-1查询失败，查询成功返回列表序号i
        for (int i = 0; i < userInformation.size(); i++) {
            if(userInformation.get(i).getUserName().equals(userName)){
                System.out.println("已查询到该用户信息");
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

    public static boolean idNumberVerify(String idNumber){
        //手机号验证，返回true
        if(idNumber.length()!=18)
            return false;
        for (int i = 0; i < idNumber.length(); i++) {
            if(idNumber.charAt(i)<'0' || idNumber.charAt(i)>'9')
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        ArrayList<User> userInformation = new ArrayList<>();
        System.out.println("欢迎使用xqchai的学生管理系统！");
        int flag = 1;
        while(flag == 1){
            System.out.println( "请输入数字1-3来进行操作\n" +
                                "**1.登录**\n" +
                                "**2.注册**\n" +
                                "**3.忘记密码**");
            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
            switch (choice){
                case 1:{
                    flag = login(userInformation);
                    break;
                }
                case 2:{
                    register(userInformation);
                    break;
                }
                case 3:{
                    forgetPassword(userInformation);
                    break;
                }
                default: {
                    System.out.println("请输入1-3之间的数字！！！");
                    break;
                }
            }
        }
    }
}
