package com.cdz.customer.bean;

import com.cdz.common.bean.ShowEntity;

import java.io.Serializable;
import java.util.Date;

public class Customer implements Serializable{

    private Integer id;

    private String customerAccount;

    private String customerPassword;

    private String customerName;

    private String customerImg;

    private Integer customerLevel;

    private Double cost;

    private String content;

    private Date createTime;

    private Integer state;

    public Customer() {
    }

    public Customer(Integer id, String customerAccount, String customerPassword, String customerName, String customerImg, Integer customerLevel, Double cost, String content, Date createTime, Integer state) {
        this.id = id;
        this.customerAccount = customerAccount;
        this.customerPassword = customerPassword;
        this.customerName = customerName;
        this.customerImg = customerImg;
        this.customerLevel = customerLevel;
        this.cost = cost;
        this.content = content;
        this.createTime = createTime;
        this.state = state;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", customerAccount='" + customerAccount + '\'' +
                ", customerPassword='" + customerPassword + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerImg='" + customerImg + '\'' +
                ", customerLevel=" + customerLevel +
                ", cost=" + cost +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", state=" + state +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomerAccount() {
        return customerAccount;
    }

    public void setCustomerAccount(String customerAccount) {
        this.customerAccount = customerAccount;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerImg() {
        return customerImg;
    }

    public void setCustomerImg(String customerImg) {
        this.customerImg = customerImg;
    }

    public Integer getCustomerLevel() {
        return customerLevel;
    }

    public void setCustomerLevel(Integer customerLevel) {
        this.customerLevel = customerLevel;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
