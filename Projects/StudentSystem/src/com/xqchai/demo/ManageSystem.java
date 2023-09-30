package com.xqchai.demo;

import java.util.ArrayList;
import java.util.Scanner;

/*增删改查初体验
**/
public class ManageSystem {
    public static void addStudent(ArrayList<Student> studentInformation){
        Student newStudent = new Student();

        System.out.println("请输入学生ID:");
        Scanner sc1 = new Scanner(System.in);
        int id = sc1.nextInt();
        newStudent.setId(id);

        System.out.println("请输入学生姓名:");
        Scanner sc2 = new Scanner(System.in);
        String name = sc2.nextLine();
        newStudent.setName(name);

        System.out.println("请输入学生性别，男生填1，女生填0:");
        Scanner sc3 = new Scanner(System.in);
        int gender = sc3.nextInt();
        newStudent.setGender(gender);

        System.out.println("请输入学生年龄:");
        Scanner sc4 = new Scanner(System.in);
        int age = sc4.nextInt();
        newStudent.setAge(age);

        System.out.println("请输入学生家庭住址:");
        Scanner sc5 = new Scanner(System.in);
        String address = sc5.nextLine();
        newStudent.setAddress(address);

        studentInformation.add(newStudent);

        System.out.println("增加学生信息成功");
        System.out.println("请继续操作：");
    }
    public static void deleteStudent(ArrayList<Student> studentInformation){
        System.out.println("请输入要删除的学生ID：");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        int flag = 0;
        if(id >=0){
            for (int i = 0; i < studentInformation.size(); i++) {
                if(studentInformation.get(i).getId()==id){
                    flag = 1;
                    System.out.println("正在删除ID为" + id + ",姓名为" + studentInformation.get(i).getName() + "的学生信息……");
                    studentInformation.remove(i);
                    //可以将id置为非法值-1来表示删除吗？
                    System.out.println("删除学生信息成功");
                }
            }
        }
        if(flag == 0){
            System.out.println("该学生不存在！删除失败！");
        }
        System.out.println("请继续操作：");
    }

    public static void checkStudent(ArrayList<Student> studentInformation){
        System.out.println("请输入学生ID来查询：");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        int flag = 0;
        if(id >=0){
            for (int i = 0; i < studentInformation.size(); i++) {
                if(studentInformation.get(i).getId()==id){
                    flag = 1;
                    System.out.println("查询学生信息成功！");
                    System.out.println("学生 ID：" + studentInformation.get(i).getId() + "\n" +
                            "学生姓名：" + studentInformation.get(i).getName() + "\n" +
                            "学生性别：" + studentInformation.get(i).getGender() + "\n" +
                            "学生年龄：" + studentInformation.get(i).getAge() + "\n" +
                            "学生住址：" + studentInformation.get(i).getAddress());
                }
            }
        }
        //id<0，即id非法时flag还是0，故不需要再写
        if(flag == 0){
            System.out.println("查询学生信息失败,该学生不存在！");
        }
        System.out.println("请继续操作：");
    }

    public static void modifyStudent(ArrayList<Student> studentInformation){
        System.out.println("请输入要修改的学生ID：");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        int flag = 0;
        if(id >=0){
            for (int i = 0; i < studentInformation.size(); i++) {
                if(studentInformation.get(i).getId()==id){
                    flag = 1;
                    System.out.println("正在修改ID为" + id + ",姓名为" + studentInformation.get(i).getName() + "的学生信息……");

                    Student newStudent = new Student();

                    System.out.println("请输入学生ID:");
                    Scanner sc1 = new Scanner(System.in);
                    int id1 = sc1.nextInt();
                    newStudent.setId(id1);

                    System.out.println("请输入学生姓名:");
                    Scanner sc2 = new Scanner(System.in);
                    String name = sc2.nextLine();
                    newStudent.setName(name);

                    System.out.println("请输入学生性别，男生填1，女生填0:");
                    Scanner sc3 = new Scanner(System.in);
                    int gender = sc3.nextInt();
                    newStudent.setGender(gender);

                    System.out.println("请输入学生年龄:");
                    Scanner sc4 = new Scanner(System.in);
                    int age = sc4.nextInt();
                    newStudent.setAge(age);

                    System.out.println("请输入学生家庭住址:");
                    Scanner sc5 = new Scanner(System.in);
                    String address = sc5.nextLine();
                    newStudent.setAddress(address);

                    studentInformation.set(i,newStudent);
                    System.out.println("修改学生信息成功");
                }
            }
        }
        if(flag == 0){
            System.out.println("该学生不存在！无法修改！");
        }
        System.out.println("请继续操作：");
    }



    public static void main(String[] args) {
        ArrayList<Student> studentInformation = new ArrayList<>();
        System.out.println("欢迎使用xqchai的学生管理系统！\n" +
                "请输入选项1-5来进行操作\n" +
                "**1.增加学生信息**\n" +
                "**2.删除学生信息**\n" +
                "**3.查找学生信息**\n" +
                "**4.更改学生信息**\n" +
                "**5.退出系统**\n" +
                "请输入您的选择：");
        int flag = 1;
        while(flag == 1){
            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
            switch (choice){
                case 1:{
                    addStudent(studentInformation);
                    break;
                }
                case 2:{
                    deleteStudent(studentInformation);
                    break;

                }
                case 3:{
                    checkStudent(studentInformation);
                    break;

                }
                case 4:{
                    modifyStudent(studentInformation);
                    break;

                }
                case 5:{
                    flag = 0;
                    System.out.println("系统已关闭");
                    break;
                }
                default: {
                    System.out.println("请输入1-5之间的数字！！！");
                    break;
                }
            }
        }
    }
}
