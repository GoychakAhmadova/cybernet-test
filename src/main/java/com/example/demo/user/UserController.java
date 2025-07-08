package com.example.demo.user;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/list")
    public List<String> getList() {
        return service.list();
    }

    // ?nickname=foo
    @GetMapping
    public User get(@RequestParam String nickname) {
        User user = service.find(nickname);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        return user;
    }


    @PostMapping
    public void insert(@Valid @RequestBody User user) {
        try {
            service.insert(user);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User exists");
        }
    }

    @PutMapping
    public void put(@RequestBody User user) {
        try {
            if (!service.findPassword(user.getNickname()).equals(user.getPassword()))
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Incorrect password");
            service.update(user);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    @DeleteMapping
    public void delete(@RequestParam String nickname, @RequestParam String password) {
        if (!service.findPassword(nickname).equals(password))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Incorrect password");
        service.delete(nickname);
    }
}