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
 * Bundle
 */
@DynamoDBTable(tableName = "BUNDLE_TABLE")
public class Bundle {
    private String id;
    private Map<String, String> items;
    private String author;
    private String status;


    public Bundle(@JsonProperty("items") Map<String, String> items, @JsonProperty("id") String id,
                  @JsonProperty("author") String author, @JsonProperty("status") String status) {
        this.items = items;
        this.author = author;
        this.status = status;
        this.id = id;
    }

    public Bundle(String id, String author) {
        this.id = id;
        this.author = author;
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

}
