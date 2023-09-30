package com.xqchai.demo;
//登录界面的用户类，标准javabean
public class User {
    private String userName;
    private String password;
    private String phoneNumber;
    private String idNumber;

    public User(){}
    public User(String userName,String password,String phoneNumber,String idNumber){
        this.userName = userName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.idNumber = idNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }
}
