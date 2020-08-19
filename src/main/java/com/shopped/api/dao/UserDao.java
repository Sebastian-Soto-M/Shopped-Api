package com.shopped.api.dao;

import java.util.List;

import com.shopped.api.model.User;

public interface UserDao {

    User create(User u);

    User update(User u);

    User delete(User u);

    User get(String id);

    List<User> getAll();

    List<User> getAllByGsi();

    User getByGsi(String email);

}
