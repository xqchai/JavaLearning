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
        newUser.setUserName(username);

        System.out.println("请输入您的密码");
        String password = enter();
        newUser.setPassword(password);

        System.out.println("请输入您的手机号");
        String phonenumber = enter();
        newUser.setPhoneNumber(phonenumber);

        System.out.println("请输入您的身份证号");
        String idnumber = enter();
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
                System.out.println("已查询到该学生信息");
                return i;
            }
        }
        return -1;
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
                    //flag = 0;//登录成功之后即可跳出循环
                    break;
                }
                case 2:{
                    register(userInformation);
                    //flag = 1;
                    break;
                }
                case 3:{
                    forgetPassword(userInformation);
                    //flag = 1;
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
