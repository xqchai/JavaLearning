package com.xqchai.user;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInformation {

    //所以用户信息都存在这里
    public static ArrayList<User> userInformation = new ArrayList<>();


    public static void initUser(){

        //测试用 管理员账号0
        User testUser = new User("0","0","12312341234");
        UserInformation.userInformation.add(testUser);

        //测试用 管理员账号1
        User testUser1 = new User("xqchai","123456","15246378873");
        UserInformation.userInformation.add(testUser1);

        //测试用 管理员账号2
        User testUser2 = new User("123","123","15246378873");
        UserInformation.userInformation.add(testUser2);


    }

}

