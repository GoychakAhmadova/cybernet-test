package com.example.demo.user;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT nickname FROM users ORDER BY nickname ASC")
    List<String> list();

    @Select("SELECT * FROM users WHERE nickname = #{nickname}")
    User find(String nickname);

    @Select("SELECT password FROM users WHERE nickname = #{nickname}")
    String findPassword(String nickname);

    @Insert("INSERT INTO users(nickname, password, name) VALUES (#{nickname}, #{password}, #{name})")
    int insert(User user);

    @Update("UPDATE users SET nickname=#{nickname}, password=#{password}, name=#{name} WHERE nickname=#{nickname}")
    int update(User user);

    @Delete("DELETE FROM users WHERE nickname=#{nickname}")
    int deleteByNickname(String nickname);
}

