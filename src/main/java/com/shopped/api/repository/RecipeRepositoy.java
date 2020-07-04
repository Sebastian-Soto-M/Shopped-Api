package com.shopped.api.repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.shopped.api.dao.RecipeDao;
import com.shopped.api.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * AwsServiceRepository
 */
@Repository("nosql-recipe")
public class RecipeRepositoy implements RecipeDao {
    private DynamoDBMapper dbMapper;

    @Autowired
    public RecipeRepositoy(AmazonDynamoDB dynamoDB) {
        this.dbMapper = new DynamoDBMapper(dynamoDB);
    }

    @Override
    public <T> T create(T t) {
        dbMapper.save(t);
        return t;
    }

    @Override
    public <T> T update(T t) {
        dbMapper.save(t);
        return t;
    }

    @Override
    public <T> T delete(T t) {
        dbMapper.delete(t);
        return t;
    }

    @Override
    public <T> List<T> getAll() {
        try {
            return (List<T>) dbMapper.scan(Tag.class, new DynamoDBScanExpression());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public <T> T get(T t) {
        return (T) dbMapper.load(Tag.class, ((Tag) t).getName(), ((Tag) t).getType());
    }
}
