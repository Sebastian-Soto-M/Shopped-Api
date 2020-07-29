package com.shopped.api.controller;

import java.util.List;

import com.shopped.api.model.User;
import com.shopped.api.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

/**
 * UserController
 */
@RequestMapping("/api/v1/user")
@RestController
public class UserController {

    private UserService us;

    @Autowired
    public UserController(UserService us) {
        this.us = us;
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return us.create(user);
    }

    @PutMapping
    public User update(@RequestBody User user) {
        return us.update(user);
    }

    @DeleteMapping(path = "{id}")
    public User delete(@PathVariable("id") String id) {
        return us.delete(new User(id));
    }

    @GetMapping(path = "{id}")
    public User get(@PathVariable("id") String id) {
        User u = us.get(new User(id));
        if (u == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        return u;
    }

    @GetMapping
    public List<User> getAll() throws RuntimeException {
        List res = us.getAll();
        if (res == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Users not found");
        }
        return res;
    }

    @GetMapping(path = "gsi")
    public List<User> getAllByGsi() {
        List res = us.getAllByGsi();
        if (res == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Users not found");
        }
        return res;
    }

    @GetMapping(path = "gsi/{email}")
    public User getByGsi(@PathVariable("email") String email) {
        User u = us.getByGsi(email);
        if (u == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        return u;
    }

}
