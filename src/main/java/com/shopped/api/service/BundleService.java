package com.shopped.api.service;

import java.util.List;

import com.shopped.api.dao.BundleDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * BundleService
 */
@Service
public class BundleService implements BundleDao {
    private final BundleDao bundleDao;

    @Autowired
    public BundleService(@Qualifier("nosql-bundle") BundleDao bundleDao) {
        this.bundleDao = bundleDao;
    }

    @Override
    public <T> T create(T t) {
        return this.bundleDao.create(t);
    }

    @Override
    public <T> T update(T t) {
        return this.bundleDao.update(t);
    }

    @Override
    public <T> T delete(T t) {
        return this.bundleDao.delete(t);
    }

    @Override
    public <T> List<T> getAll() {
        return this.bundleDao.getAll();
    }

    @Override
    public <T> T get(T t) {
        return this.bundleDao.get(t);
    }

    @Override
    public <T> List<T> getAllByAuthor(T t) { return this.bundleDao.getAllByAuthor(t);   }
}
