package com.shopped.api.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

/**
 * Bundle
 */
@DynamoDBTable(tableName = "SHOPPING_LIST_TABLE")
public class Bundle {
    private String id;
    private List<String> items;
    private String author;
    private String status;
    private String type;

    public Bundle(@JsonProperty("items") List<String> items, @JsonProperty("id") String id,
                  @JsonProperty("author") String author, @JsonProperty("status") String status, @JsonProperty("type") String type) {
        this.items = items;
        this.author = author;
        this.status = status;
        this.type = type;
        this.id=id;
    }

    public Bundle(String id, String type) {
        this.id=id;
        this.type=type;
    }

    public Bundle() {
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
    public void setStatus(String status) { this.status = status;    }

    @DynamoDBAttribute(attributeName = "TYPE")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @DynamoDBAttribute(attributeName = "ITEMS")
    public List<String>getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    @DynamoDBAttribute(attributeName = "AUTHOR")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }



}
