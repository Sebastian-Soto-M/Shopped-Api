package com.shopped.api.controller;

import java.util.List;

import com.shopped.api.model.User;
import com.shopped.api.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        return us.delete(id);
    }

    @GetMapping(path = "{id}")
    public User get(@PathVariable("id") String id) {
        return us.get(id);
    }

    @GetMapping
    public List<User> getAll() {
        return us.getAll();
    }

    @GetMapping(path = "gsi")
    public List<User> getAllByGsi() {
        return us.getAllByGsi();
    }

    @GetMapping(path = "gsi/{email}")
    public User getByGsi(@PathVariable("email") String email) {
        User u = us.getByGsi(email);
        return u;
    }

}
