package com.shopped.api.service;

import java.util.List;
import java.util.Optional;

import com.shopped.api.dao.TagDao;
import com.shopped.api.model.Tag;

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
    public boolean createTag(Tag tag) {
        return tagDao.createTag(tag);
    }

    @Override
    public boolean updateTag(Tag tag) {
        return tagDao.updateTag(tag);
    }

    @Override
    public boolean deleteTag(Tag tag) {
        return tagDao.deleteTag(tag);
    }

    @Override
    public List<Tag> getAllTags() {
        return tagDao.getAllTags();
    }

    @Override
    public Optional<Tag> getTagByName(String name) {
        return tagDao.getTagByName(name);
    }

}
