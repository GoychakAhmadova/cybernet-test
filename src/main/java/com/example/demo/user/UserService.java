package com.example.demo.user;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserMapper mapper;

    public UserService(UserMapper mapper) {
        this.mapper = mapper;
    }

    public List<String> list() {
        return mapper.list();
    }

    @Cacheable(value = "users", key = "#nickname")
    public User find(String nickname) {
        User user = mapper.find(nickname);
        if (user != null) System.out.println("Get User: " + user);
        else System.err.println("Get User (" + nickname + "): No Found");
        return user;
    }

    public String findPassword(String nickname) {
        return mapper.findPassword(nickname);
    }

    public void insert(User user) {
        if (user == null) return;
        try {
            if (mapper.insert(user) > 0) System.out.println("Insert User: " + user);
            else System.err.println("Insert User (" + user.getNickname() + "): No Create");
        } catch (DuplicateKeyException e) {
            throw new IllegalArgumentException("Nickname already taken");
        }
    }

    @CacheEvict(value = "users", key = "#user.nickname")
    public void update(User user) {
        if (user == null) return;
        if (mapper.update(user) > 0) System.out.println("Update User: " + user);
        else {
            System.err.println("Update User (" + user.getNickname() + "): No Updated");
            throw new IllegalArgumentException("User no updated");
        }
    }

    @CacheEvict(value = "users", key = "#nickname")
    public void delete(String nickname) {
        User deletedUser = mapper.find(nickname);
        if (deletedUser != null && mapper.deleteByNickname(nickname) > 0)
            System.out.println("Delete User: " + deletedUser);
        else System.err.println("Delete User (" + nickname + "): No Found");
    }
}