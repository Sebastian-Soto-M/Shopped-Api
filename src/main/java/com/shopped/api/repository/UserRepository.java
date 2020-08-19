package com.shopped.api.repository;

import java.util.HashMap;
import java.util.List;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
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
    public User create(User u) {
        dbMapper.save(u);
        return u;
    }

    @Override
    public User update(User u) {
        dbMapper.save(u);
        return u;
    }

    @Override
    public User delete(User u) {
        dbMapper.save(u);
        return u;
    }

    @Override
    public User get(String id) {
        User u = dbMapper.load(User.class, id, "ACTIVE");
        return (u != null) ? u : new User();
    }

    @Override
    public List<User> getAll() {
        try {
            return dbMapper.scan(User.class, new DynamoDBScanExpression());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<User> getAllByGsi() {
        try {
            DynamoDBScanExpression scanExpression = new DynamoDBScanExpression().withIndexName("EMAIL")
                    .withConsistentRead(false);
            return dbMapper.scan(User.class, scanExpression);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public User getByGsi(String email) {
        HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":email", new AttributeValue().withS(email));
        try {
            DynamoDBQueryExpression<User> queryExpression = new DynamoDBQueryExpression<User>().withIndexName("EMAIL")
                    .withConsistentRead(false).withKeyConditionExpression("EMAIL = :email")
                    .withExpressionAttributeValues(eav);
            return dbMapper.query(User.class, queryExpression).get(0);
        } catch (Exception e) {
            return new User();
        }
    }

}
