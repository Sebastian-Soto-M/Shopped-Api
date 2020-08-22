package com.shopped.api.service;

import java.util.List;

import com.shopped.api.dao.CartDao;
import com.shopped.api.model.Cart;
import com.shopped.api.model.Recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * CartService
 */
@Service
public class CartService {
    private final CartDao cartDao;

    @Autowired
    public CartService(@Qualifier("nosql-cart") CartDao cartDao) {
        this.cartDao = cartDao;
    }

    public Cart replaceCart(String author) {
        return cartDao.replaceCart(author);
    }

    public List<Cart> getAllByAuthor(String author) {
        return cartDao.getAllByAuthor(author);
    }

    public Cart getCurrentByAuthor(String author) {
        Cart current = cartDao.getCurrentByAuthor(author);
        if (current != null) {
            return current;
        } else {
            return cartDao.create(author);
        }
    }

    public Cart update(Cart c) {
        return cartDao.update(c);
    }

    public Cart mergeRecipe(Recipe r) {
        Cart c = cartDao.getCurrentByAuthor(r.getAuthor());
        return cartDao.mergeRecipe(c, r);
    }

}
