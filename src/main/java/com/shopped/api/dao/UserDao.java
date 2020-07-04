package com.shopped.api.dao;

import java.util.List;

import com.shopped.api.model.User;

public interface UserDao extends BaseDao {
    List<User> getAllByGsi();

    User getByGsi(String email);
}
