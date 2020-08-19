package com.shopped.api.service;

import java.util.List;

import com.shopped.api.dao.UserDao;
import com.shopped.api.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * UserService
 */
@Service
public class UserService {
    private final UserDao userDao;

    @Autowired
    public UserService(@Qualifier("nosql-user") UserDao userDao) {
        this.userDao = userDao;
    }

    public User create(User u) {
        u.setStatus("ACTIVE");
        return userDao.create(u);
    }

    public User update(User u) {
        return userDao.update(u);
    }

    public User delete(String id) {
        User u = userDao.get(id);
        u.setStatus("INACTIVE");
        return userDao.delete(u);
    }

    public User get(String id) {
        return userDao.get(id);
    }

    public List<User> getAll() {
        return userDao.getAll();
    }

    public List<User> getAllByGsi() {
        return userDao.getAllByGsi();
    }

    public User getByGsi(String email) {
        return userDao.getByGsi(email);
    }

}
