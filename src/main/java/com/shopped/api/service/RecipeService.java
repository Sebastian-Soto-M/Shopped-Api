package com.shopped.api.service;

import java.util.List;

import com.shopped.api.dao.RecipeDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


/**
 * RecipeService
 */
@Service
public class RecipeService implements RecipeDao{
    private final RecipeDao recipeDao;

    @Autowired
    public RecipeService(@Qualifier("nosql-recipe") RecipeDao recipeDao) {
        this.recipeDao = recipeDao;
    }

    @Override
    public <T> T create(T t) {
        return this.recipeDao.create(t);
    }

    @Override
    public <T> T update(T t) {
        return this.recipeDao.update(t);
    }

    @Override
    public <T> T delete(T t) {
        return this.recipeDao.delete(t);
    }

    @Override
    public <T> List<T> getAll() {
        return this.recipeDao.getAll();
    }

    @Override
    public <T> T get(T t) {
        return this.recipeDao.get(t);
    }
}
