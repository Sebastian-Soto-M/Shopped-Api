package com.shopped.api.dao;

import java.util.List;

/**
 * BaseDao
 */
public interface BaseDao {

    <T> T create(T t);

    <T> T update(T t);

    <T> T delete(T t);

    <T> List<T> getAll();

    <T> T get(T t);

}
