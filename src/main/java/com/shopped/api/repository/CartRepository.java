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
import com.shopped.api.model.Recipe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * AwsServiceRepository
 */
@Repository("nosql-cart")
public class CartRepository implements CartDao {

    private DynamoDBMapper dbMapper;

    private final Logger logger = LoggerFactory.getLogger(CartRepository.class);

    @Autowired
    public CartRepository(AmazonDynamoDB dynamoDB) {
        this.dbMapper = new DynamoDBMapper(dynamoDB);
    }

    @Override
    public Cart getCurrentByAuthor(String author) {
        HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":author", new AttributeValue().withS(author));
        try {
            logger.info("Author:\t" + author);
            DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                    .withFilterExpression("AUTHOR = :author").withIndexName("AUTHOR_INDEX").withConsistentRead(false)
                    .withExpressionAttributeValues(eav);
            Cart cart = dbMapper.scan(Cart.class, scanExpression).stream().filter(c -> c.getStatus().equals("ACTIVE"))
                    .findFirst().orElse(null);
            return cart;
        } catch (NullPointerException npe) {
            logger.info("The author had no current carts");
            return create(author);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            return null;
        }
    }

    @Override
    public Cart replaceCart(String author) {
        Cart current = getCurrentByAuthor(author);
        current.setStatus("INACTIVE");
        dbMapper.save(current);
        Cart cNew = new Cart();
        cNew.setAuthor(author);
        cNew.setStatus("ACTIVE");
        dbMapper.save(cNew);
        logger.info("Replacing cart... Old one set INACTIVE, New created");
        return getCurrentByAuthor(author);
    }

    @Override
    public Cart update(Cart c) {
        Cart current = getCurrentByAuthor(c.getAuthor());
        current.mergeItems(c.getItems());
        dbMapper.save(current);
        return current;
    }

    @Override
    public Cart mergeRecipe(Cart c, Recipe r) {
        c.mergeItems(r.getItems());
        return c;
    }

    @Override
    public List<Cart> getAllByAuthor(String author) {
        HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":author", new AttributeValue().withS(author));
        try {
            DynamoDBQueryExpression<Cart> queryExpression = new DynamoDBQueryExpression<Cart>()
                    .withIndexName("AUTHOR_INDEX").withConsistentRead(false)
                    .withKeyConditionExpression("AUTHOR= :author").withExpressionAttributeValues(eav);
            return dbMapper.query(Cart.class, queryExpression);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Cart create(String author) {
        Cart c = new Cart();
        c.setAuthor(author);
        c.setStatus("ACTIVE");
        logger.info("Set variables for new cart:\t" + author);
        dbMapper.save(c);
        logger.info("New cart assigned to author:\t" + author);
        try {
            return getCurrentByAuthor(author);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            return null;
        }
    }

}
