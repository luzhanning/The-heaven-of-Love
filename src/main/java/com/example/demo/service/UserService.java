package com.example.demo.service;


import cn.hutool.crypto.SecureUtil;
import com.example.demo.dao.UserMapper;
import com.example.demo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class UserService {
    @Autowired
    UserMapper usermapper;


    public Boolean Login(String idValue, String passwordValue) {
            User user = new User(idValue,passwordValue,"1","2","3");
            if (String.valueOf(usermapper.login(user)).length() == 2) {
                return false;
            }
        return true;
    }

    public Boolean Register(String idValue, String passwordValue,String passwordValue2,String nickname,String sex,String sex1) {
        if (!passwordValue.equals(passwordValue2)) {
            return false;
        }
        User user = new User(idValue,passwordValue,nickname,sex,sex1);
        user.setPassword(SecureUtil.md5(user.getPassword()));
        if (String.valueOf(usermapper.userExists(user)).length() == 2) {
            usermapper.insert(user);
            return true;
        }
        return true;
    }

    public Integer getScore(Score score){
        return usermapper.getScore(score);
    }

    public  void setScore(Score score){
        usermapper.setScore(score);
    }

    public  void insertScore(Score score){
        usermapper.insertScore(score);
    }

    public void hitPlusOne(Integer type){
        usermapper.hitPlusOne(type);
    }
    public List<Score> getRankByType(Integer type){
        return usermapper.getRankByType(type);
    }
    public List<hit> getHit(){
        return usermapper.getHit();
    }
    public Custom getCustom(Custom custom){
        return usermapper.getCustom(custom);
    }
    public void insertCustom(Custom custom){
         usermapper.insertCustom(custom);
    }

    public void setCustom(Custom custom){
        usermapper.setCustom(custom);
    }

    public List<Custom> getCustomRank(){
        return usermapper.getCustomRank();
    }

    public Chuangguan getChuang(String nickname){
        return usermapper.getChuang(nickname);
    }
    public void insertChuang(String nickname){
        usermapper.insertChuang(nickname);
    }

    public void Chuang(Chuangguan chuangguan){
        usermapper.Chuang(chuangguan);
    }
}

