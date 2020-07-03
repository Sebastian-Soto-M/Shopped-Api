package com.shopped.api.service;

import java.util.List;

import com.shopped.api.dao.UserDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * UserService
 */
@Service
public class UserService implements UserDao {
    private final UserDao userDao;

    @Autowired
    public UserService(@Qualifier("nosql-user") UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public <T> T create(T t) {
        return this.userDao.create(t);
    }

    @Override
    public <T> T update(T t) {
        return this.userDao.update(t);
    }

    @Override
    public <T> T delete(T t) {
        return this.userDao.delete(t);
    }

    @Override
    public <T> List<T> getAll() {
        return this.userDao.getAll();
    }

    @Override
    public <T> T get(T t) {
        return this.userDao.get(t);
    }

}
