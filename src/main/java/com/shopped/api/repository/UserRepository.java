package com.shopped.api.repository;

import java.util.HashMap;
import java.util.List;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.shopped.api.dao.UserDao;
import com.shopped.api.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * AwsServiceRepository
 */
@Repository("nosql-user")
public class UserRepository implements UserDao {

    private DynamoDBMapper dbMapper;

    @Autowired
    public UserRepository(AmazonDynamoDB dynamoDB) {
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
            return (List<T>) dbMapper.scan(User.class, new DynamoDBScanExpression());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public <T> T get(T t) {
        return (T) dbMapper.load(User.class, ((User) t).getId(), ((User) t).getStatus());
    }

    @Override
    public List<User> getAllByGsi() {
        try {
            DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
            .withIndexName("EMAIL")
            .withConsistentRead(false);
            return (List<User>) dbMapper.scan(User.class, scanExpression);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public User getByGsi(String email) {
        HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":email", new AttributeValue().withS(email));

        try {
            DynamoDBQueryExpression<User> queryExpression = new DynamoDBQueryExpression<User>()
            .withIndexName("EMAIL")
            .withConsistentRead(false)
            .withKeyConditionExpression("EMAIL = :email")
            .withExpressionAttributeValues(eav);
            return ((List<User>) dbMapper.query(User.class, queryExpression)).get(0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

}
