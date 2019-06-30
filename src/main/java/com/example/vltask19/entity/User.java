package com.example.vltask19.entity;


import java.io.Serializable;
import java.util.Date;

/**
 * 类型
 */
public class User implements Serializable {
    private int userId;
    private String userName;
    private String password;
    private Date loginTime;

    public User(String userName,String password){
        this.userName = userName;
        this.password = password;
    }

    public User(){}

    public long getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
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

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

}
