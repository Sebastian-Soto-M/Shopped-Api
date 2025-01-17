package com.shopped.api.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import java.util.Map;

/**
 * Recipe
 */
@DynamoDBTable(tableName = "RECIPE_TABLE")
public class Recipe {
    private String id;
    private Map<String, String> items;
    private String author;
    private String status;
    private Map<String, String> data;
    private Map<String, String> steps;

    public Recipe(@JsonProperty("data") Map<String, String> data, @JsonProperty("items") Map<String, String> items, @JsonProperty("id") String id,
                  @JsonProperty("author") String author, @JsonProperty("status") String status, @JsonProperty("type") String type, @JsonProperty("steps") Map<String, String> steps) {
        this.items = items;
        this.author = author;
        this.status = status;
        this.id = id;
        this.data = data;
        this.steps = steps;
    }

    public Recipe(String id, String author) {
        this.id = id;
        this.author = author;
    }

    public Recipe() {
    }

    public Recipe(String author) {
        this.author=author;
    }

    @DynamoDBHashKey(attributeName = "ID")
    @DynamoDBAutoGeneratedKey
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @DynamoDBRangeKey(attributeName = "AUTHOR")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @DynamoDBAttribute(attributeName = "STATUS")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @DynamoDBAttribute(attributeName = "ITEMS")
    public Map<String, String> getItems() {
        return items;
    }

    public void setItems(Map<String, String> items) {
        this.items = items;
    }

    @DynamoDBAttribute(attributeName = "DATA")
    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

     @DynamoDBAttribute(attributeName = "STEPS")
    public Map<String, String> getSteps() {
        return steps;
    }

    public void setSteps(Map<String, String> steps) {
        this.steps = steps;
    }
    
    
}
