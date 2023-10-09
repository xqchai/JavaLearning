package com.xqchai.user;


public class User {
    /*用户类，标准javabean
     **/
    private String account;
    private String password;
    private String phoneNumber;

    public User(){}

    public User(String account,String password,String phoneNumber){
        this.account = account;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
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
}
