package com.example.demo.dao;

import com.example.demo.model.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


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

    Integer getScore(Score score);

    void setScore(Score score);

    void insertScore(Score score);

    void hitPlusOne(@Param("type") Integer type);

    List<Score> getRankByType(@Param("type") Integer type);

    List<hit> getHit();

    Custom getCustom(Custom custom);

    void insertCustom(Custom custom);

    void setCustom(Custom custom);

    List<Custom> getCustomRank();

    Chuangguan getChuang(@Param("nickname")String nickname);

    void insertChuang(@Param("nickname")String nickname);

    void Chuang(Chuangguan chuangguan);
}