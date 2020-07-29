package com.shopped.api.controller;

import java.util.Collections;
import java.util.List;

import com.shopped.api.model.User;
import com.shopped.api.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        User u = us.get(user);
        return (u != null) ? us.update(user) : new User();
    }

    @DeleteMapping(path = "{id}")
    public User delete(@PathVariable("id") String id) {
        return us.delete(new User(id));
    }

    @GetMapping(path = "{id}")
    public User get(@PathVariable("id") String id) {
        User u = us.get(new User(id));
        return (u != null) ? u : new User();
    }

    @GetMapping
    public List<User> getAll() throws RuntimeException {
        List<User> u = us.getAll();
        return (u != null) ? u : Collections.emptyList();
    }

    @GetMapping(path = "gsi")
    public List<User> getAllByGsi() {
        List<User> u = us.getAllByGsi();
        return (u != null) ? u : Collections.emptyList();
    }

    @GetMapping(path = "gsi/{email}")
    public User getByGsi(@PathVariable("email") String email) {
        User u = us.getByGsi(email);
        return (u != null) ? u : new User();
    }

}
