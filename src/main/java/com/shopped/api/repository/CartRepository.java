package com.shopped.api.repository;

import java.util.HashMap;
import java.util.List;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.shopped.api.dao.CartDao;
import com.shopped.api.model.Cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * AwsServiceRepository
 */
@Repository("nosql-cart")
public class CartRepository implements CartDao {

    private DynamoDBMapper dbMapper;

    @Autowired
    public CartRepository(AmazonDynamoDB dynamoDB) {
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
            return (List<T>) dbMapper.scan(Cart.class, new DynamoDBScanExpression());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public <T> T get(T t) {
        return (T) dbMapper.load(Cart.class, ((Cart) t).getId(),((Cart) t).getAuthor());
    }

    @Override
    public <T> List<T> getAllByAuthor(T t) {
        HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":author", new AttributeValue().withS(((Cart) t).getAuthor()));

        try {
            DynamoDBQueryExpression<Cart> queryExpression = new DynamoDBQueryExpression<Cart>()
                    .withIndexName("AUTHOR_INDEX")
                    .withConsistentRead(false)
                    .withKeyConditionExpression("AUTHOR= :author")
                    .withExpressionAttributeValues(eav);
            return (List<T>) dbMapper.query(Cart.class, queryExpression);
        } catch (Exception e) {
            return null;
        }
    }
}