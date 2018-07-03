package com.example.demo.controller.User;


import cn.hutool.crypto.SecureUtil;
import com.example.demo.UserMapper;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.Arrays;
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

}
