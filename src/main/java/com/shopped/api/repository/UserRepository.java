package com.shopped.api.repository;

import java.util.List;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
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

}
