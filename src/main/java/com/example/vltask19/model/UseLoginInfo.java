package com.example.vltask19.model;

import java.util.Date;

public class UseLoginInfo {
    private Integer id;
    private String name;
    private String password;
    private Date loginTime;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    @Override
    public int hashCode() {
        return (name + password).hashCode();
    }
}
