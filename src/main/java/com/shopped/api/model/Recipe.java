package com.shopped.api.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
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
    private List<String> instructions;

    public Recipe(@JsonProperty("instructions") List<String> instructions, @JsonProperty("items") Map<String, String> items, @JsonProperty("id") String id,
                  @JsonProperty("author") String author, @JsonProperty("status") String status, @JsonProperty("type") String type) {
        this.items = items;
        this.author = author;
        this.status = status;
        this.id = id;
        this.instructions = instructions;
    }

    public Recipe(String id, String author) {
        this.id = id;
        this.author = author;
    }

    public Recipe() {
    }

    @DynamoDBHashKey(attributeName = "ID")
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

    @DynamoDBAttribute(attributeName = "INSTRUCTIONS")
    public List<String> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<String> instructions) {
        this.instructions = instructions;
    }
}
