package com.shopped.api.service;

import java.util.List;

import com.shopped.api.dao.TagDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * TagService
 */
@Service
public class TagService implements TagDao {
    private final TagDao tagDao;

    @Autowired
    public TagService(@Qualifier("nosql") TagDao tagDao) {
        this.tagDao = tagDao;
    }

    @Override
    public <T> T create(T t) {
        return this.tagDao.create(t);
    }

    @Override
    public <T> T update(T t) {
        return this.tagDao.update(t);
    }

    @Override
    public <T> T delete(T t) {
        return this.tagDao.delete(t);
    }

    @Override
    public <T> List<T> getAll() {
        return this.tagDao.getAll();
    }

    @Override
    public <T> T get(T t) {
        return this.tagDao.get(t);
    }

}
