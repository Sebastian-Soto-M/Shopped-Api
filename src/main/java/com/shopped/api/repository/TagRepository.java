package com.shopped.api.repository;

import java.util.List;
import java.util.Optional;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.shopped.api.dao.TagDao;
import com.shopped.api.model.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * AwsServiceRepository
 */
@Repository("nosql")
public class TagRepository implements TagDao {

    private DynamoDBMapper dbMapper;

    @Autowired
    public TagRepository(AmazonDynamoDB dynamoDB) {
        this.dbMapper = new DynamoDBMapper(dynamoDB);
    }

    @Override
    public boolean createTag(Tag tag) {
        try {
            dbMapper.save(tag);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateTag(Tag tag) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean deleteTag(Tag tag) {
        try {
            dbMapper.delete(tag);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Tag> getAllTags() {
        return null;
    }

    @Override
    public Optional<Tag> getTagByName(String name) {
        return null;
    }

}
