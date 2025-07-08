package com.example.demo.user;

import jakarta.validation.constraints.NotBlank;

import java.io.Serial;
import java.io.Serializable;

public class User implements Serializable { // Serializable, чтобы БД мог записывать в класс
    @Serial
    private static final long serialVersionUID = 1;

    @NotBlank(message = "Nickname is empty") // @NotBlank не позволят оставлять поле пустым
    private String nickname;

    @NotBlank(message = "Password is empty")
    private String password; // Пока хеширования нет! А оно мне надо?!

    @NotBlank(message = "Name is empty")
    private String name;

    public User() {
    }

    public User(String nickname, String password, String name) {
        this.nickname = nickname;
        this.password = password;
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User (" + nickname + ") = {password: " + password + ", name: " + name + "}";
    }
}
