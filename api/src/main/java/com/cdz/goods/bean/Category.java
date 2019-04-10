package com.cdz.goods.bean;


import javax.persistence.*;
import java.io.Serializable;

public class Category implements Serializable{

    private Integer id;

    private String value;

    private Integer categoryId;

    private Integer sellerId;

    public Category() {
    }

    public Category(Integer id, String value, Integer categoryId, Integer sellerId) {
        this.id = id;
        this.value = value;
        this.categoryId = categoryId;
        this.sellerId = sellerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }
}
