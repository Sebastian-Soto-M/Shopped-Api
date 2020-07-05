package com.shopped.api.dao;

import java.util.List;

public interface ShoppingListDao extends BaseDao{
    <T> List<T> getAllByAuthor(T t);
}
