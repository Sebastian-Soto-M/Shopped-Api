package com.shopped.api.dao;

import java.util.List;
import java.util.Optional;

import com.shopped.api.model.Tag;

public interface TagDao {

    boolean createTag(Tag tag);

    boolean updateTag(Tag tag);

    boolean deleteTag(Tag tag);

    List<Tag> getAllTags();

    Optional<Tag> getTagByName(String name, String type);

}
