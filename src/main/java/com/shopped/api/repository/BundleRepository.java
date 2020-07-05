package com.shopped.api.repository;

import java.util.HashMap;
import java.util.List;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.shopped.api.dao.BundleDao;
import com.shopped.api.model.Bundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * AwsServiceRepository
 */
@Repository("nosql-bundle")
public class BundleRepository implements BundleDao {

    private DynamoDBMapper dbMapper;

    @Autowired
    public BundleRepository(AmazonDynamoDB dynamoDB) {
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
            return (List<T>) dbMapper.scan(Bundle.class, new DynamoDBScanExpression());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public <T> T get(T t) {
        return (T) dbMapper.load(Bundle.class, ((Bundle) t).getId(),((Bundle) t).getAuthor());
    }

    @Override
    public <T> List<T> getAllByAuthor(T t) {
        HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":author", new AttributeValue().withS(((Bundle) t).getAuthor()));

        try {
            DynamoDBQueryExpression<Bundle> queryExpression = new DynamoDBQueryExpression<Bundle>()
                    .withIndexName("AUTHOR_INDEX")
                    .withConsistentRead(false)
                    .withKeyConditionExpression("AUTHOR= :author")
                    .withExpressionAttributeValues(eav);
            return (List<T>) dbMapper.query(Bundle.class, queryExpression);
        } catch (Exception e) {
            return null;
        }
    }
}
