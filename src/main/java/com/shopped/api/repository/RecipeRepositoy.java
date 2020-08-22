package com.shopped.api.repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.shopped.api.dao.RecipeDao;
import com.shopped.api.model.Bundle;
import com.shopped.api.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
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
            return (List<T>) dbMapper.scan(Recipe.class, new DynamoDBScanExpression());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public <T> T get(T t) {
        return (T) dbMapper.load(Recipe.class, ((Recipe) t).getId(), ((Recipe) t).getAuthor());
    }

    @Override
    public <T> List<T> getAllByAuthor(T t) {
        HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":author", new AttributeValue().withS(((Recipe) t).getAuthor()));


        try {
            DynamoDBQueryExpression<Recipe> queryExpression = new DynamoDBQueryExpression<Recipe>()
                    .withIndexName("AUTHOR_INDEX")
                    .withConsistentRead(false)
                    .withKeyConditionExpression("AUTHOR= :author")
                    .withExpressionAttributeValues(eav);
            return (List<T>) dbMapper.query(Recipe.class, queryExpression);
        } catch (Exception e) {
            return null;
        }
    }
}
