package com.shopped.api.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Tag
 */
@DynamoDBTable(tableName = "TAG_TABLE")
public class Tag {
    private String name;
    private String type;
    private String color;
    private String status;

    public Tag(@JsonProperty("NAME") String name, @JsonProperty("TYPE") String type,
            @JsonProperty("COLOR") String color, @JsonProperty("STATUS") String status) {
        this.name = name;
        this.type = type;
        this.color = color;
        this.status = status;
    }

    @DynamoDBHashKey(attributeName = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDBRangeKey(attributeName = "TYPE")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @DynamoDBAttribute(attributeName = "COLOR")
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @DynamoDBAttribute(attributeName = "STATUS")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
