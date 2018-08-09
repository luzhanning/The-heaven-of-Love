package com.example.demo.model;


public class User {

    private String password;
    private String id;
    private String nickname;
    private String sex;
    private String sex1;

    public User(String id,String password,String nickname,String sex,String sex1) {
        this.password = password;
        this.id = id;
        this.nickname = nickname;
        this.sex = sex;
        this.sex1 = sex1;
    }

    public User(){ }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public String getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSex1() {
        return sex1;
    }

    public void setSex1(String sex1) {
        this.sex1 = sex1;
    }
}
