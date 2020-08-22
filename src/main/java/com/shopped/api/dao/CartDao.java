package com.shopped.api.dao;

import java.util.List;

import com.shopped.api.model.Cart;
import com.shopped.api.model.Recipe;

public interface CartDao {

    Cart replaceCart(String author);

    Cart getCurrentByAuthor(String author);

    Cart update(Cart c);

    Cart mergeRecipe(Cart c, Recipe r);

    List<Cart> getAllByAuthor(String author);

    Cart create(String author);

}
