package com.shopped.api.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
/**
 * Recipe
 */
@DynamoDBTable(tableName = "SHOPPING_LIST_TABLE")
public class Recipe {
    private String id;
    private ArrayList<String> instructions;
    private ArrayList<String> items;
    private String author;
    private String status;
    private double duration;
    private String creationDate;

    public Recipe(@JsonProperty("instructions") ArrayList <String> instructions,@JsonProperty("items") ArrayList <String> items,
                  @JsonProperty("author") String author, @JsonProperty("status") String status, @JsonProperty("creationDate") String creationDate, @JsonProperty("duration") double duration) {
        this.items = items;
        this.author = author;
        this.status = status;
        this.creationDate = creationDate;
        this.duration = duration;
        this.instructions=instructions;

    }

    public Recipe() {
    }

    public Recipe(String id) {
        this.id=id;
    }

    @DynamoDBHashKey(attributeName = "META")
    public ArrayList<String> getInstructions() {
        return instructions;
    }

    public void setInstructions(ArrayList<String> instructions) {
        this.instructions = instructions;
    }

    @DynamoDBHashKey(attributeName = "ITEMS")

    public ArrayList<String> getItems() {
        return items;
    }

    public void setItems(ArrayList<String> items) {
        this.items = items;
    }

    @DynamoDBHashKey(attributeName = "USER")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @DynamoDBHashKey(attributeName = "STATUS")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @DynamoDBHashKey(attributeName = "DURATION")
    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    @DynamoDBHashKey(attributeName = "CREATION_DATE")

    public String getCreationDate() {
        return creationDate;
    }

    public void creationDate(String date) {
        this.creationDate = date;
    }
}
