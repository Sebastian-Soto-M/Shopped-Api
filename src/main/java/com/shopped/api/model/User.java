package com.shopped.api.model;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * User
 */
@DynamoDBTable(tableName = "USER_TABLE")
public class User {
    private String id;
    private String status;
    private String name;
    private String email;
    private String password;
    private String birthDate;
    private List<String> shoppingLists;
    private int rating;
    private int ratingAmount;
    private int ratingSum;

    public User(@JsonProperty("id") String id, @JsonProperty("name") String name, @JsonProperty("email") String email,
                @JsonProperty("password") String password, @JsonProperty("birthDate") String birthDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
    }

    public User(String id, String status) {
        this.id = id;
        this.status = status;
    }

    public User() {
    }

    @DynamoDBHashKey(attributeName = "ID")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @DynamoDBRangeKey(attributeName = "STATUS")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @DynamoDBAttribute(attributeName = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDBIndexHashKey(attributeName = "EMAIL", globalSecondaryIndexName = ("EMAIL"))
    @DynamoDBAttribute(attributeName = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @DynamoDBAttribute(attributeName = "PASSWORD")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @DynamoDBAttribute(attributeName = "BIRTHDAY")
    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    @DynamoDBAttribute(attributeName = "SHOPPING_LISTS")
    public List<String> getShoppingLists() {
        return shoppingLists;
    }

    public void setShoppingLists(List<String> shoppingLists) {
        this.shoppingLists = shoppingLists;
    }

    @DynamoDBAttribute(attributeName = "RATING")
    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @DynamoDBAttribute(attributeName = "RATING_AMOUNT")
    public int getRatingAmount() {
        return ratingAmount;
    }

    public void setRatingAmount(int ratingAmount) {
        this.ratingAmount = ratingAmount;
    }

    @DynamoDBAttribute(attributeName = "RATING_SUM")
    public int getRatingSum() {
        return ratingSum;
    }

    public void setRatingSum(int ratingSum) {
        this.ratingSum = ratingSum;
    }

}
