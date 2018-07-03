package com.example.demo;

import model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    String getNickname(String id);
    String getSex(String id);
    String getSex1(String id);
    List<User> findAll();
    void delete(String id);
    void insert(User users);
    List<User> userExists(User user);
    List<User> login(User user);
}