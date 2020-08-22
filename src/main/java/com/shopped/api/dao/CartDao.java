package com.shopped.api.dao;

import com.shopped.api.model.Cart;

public interface CartDao extends ShoppingListDao {

    Cart getCurrentByAuthor(String author);

}
