package com.cdz.common.bean;

import java.io.Serializable;

public class ShowEntity implements Serializable{

    private String categoryValue;

    private String goodsStateValue;

    private String goodsValue;

    private String sellerValue;

    private String customerValue;

    public String getCategoryValue() {
        return categoryValue;
    }

    public void setCategoryValue(String categoryValue) {
        this.categoryValue = categoryValue;
    }

    public String getGoodsStateValue() {
        return goodsStateValue;
    }

    public void setGoodsStateValue(String goodsStateValue) {
        this.goodsStateValue = goodsStateValue;
    }

    public String getGoodsValue() {
        return goodsValue;
    }

    public void setGoodsValue(String goodsValue) {
        this.goodsValue = goodsValue;
    }

    public String getSellerValue() {
        return sellerValue;
    }

    public void setSellerValue(String sellerValue) {
        this.sellerValue = sellerValue;
    }

    public String getCustomerValue() {
        return customerValue;
    }

    public void setCustomerValue(String customerValue) {
        this.customerValue = customerValue;
    }
}
