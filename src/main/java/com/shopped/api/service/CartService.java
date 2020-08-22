package com.shopped.api.service;

import java.util.List;

import com.shopped.api.dao.CartDao;
import com.shopped.api.model.Cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * CartService
 */
@Service
public class CartService implements CartDao {
    private final CartDao cartDao;

    @Autowired
    public CartService(@Qualifier("nosql-cart") CartDao cartDao) {
        this.cartDao = cartDao;
    }

    @Override
    public <T> T create(T t) {
        return this.cartDao.create(t);
    }

    @Override
    public <T> T update(T t) {
        return this.cartDao.update(t);
    }

    @Override
    public <T> T delete(T t) {
        return this.cartDao.delete(t);
    }

    @Override
    public <T> List<T> getAll() {
        return this.cartDao.getAll();
    }

    @Override
    public <T> T get(T t) {
        return this.cartDao.get(t);
    }

    @Override
    public <T> List<T> getAllByAuthor(T t) {
        return this.cartDao.getAllByAuthor(t);
    }

    public Cart getCurrentByAuthor(String author) {
        return this.cartDao.getCurrentByAuthor(author);
    }
}
