package com.cdz.seller.bean;

import com.cdz.common.bean.ShowEntity;

import java.io.Serializable;
import java.util.Date;

public class Seller extends ShowEntity implements Serializable{

    private Integer id;

    private String sellerName;

    private Integer sellerLevel;

    private String sellerAccount;

    private String sellerPassword;

    private Date createTime;

    private Integer state;

    public Seller() {
    }

    public Seller(String sellerName, Integer sellerLevel, String sellerAccount, String sellerPassword, Date createTime, Integer state) {
        this.sellerName = sellerName;
        this.sellerLevel = sellerLevel;
        this.sellerAccount = sellerAccount;
        this.sellerPassword = sellerPassword;
        this.createTime = createTime;
        this.state = state;
    }

    @Override
    public String toString() {
        return "Seller{" +
                "id=" + id +
                ", sellerName='" + sellerName + '\'' +
                ", sellerLevel=" + sellerLevel +
                ", sellerAccount='" + sellerAccount + '\'' +
                ", sellerPassword='" + sellerPassword + '\'' +
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

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public Integer getSellerLevel() {
        return sellerLevel;
    }

    public void setSellerLevel(Integer sellerLevel) {
        this.sellerLevel = sellerLevel;
    }

    public String getSellerAccount() {
        return sellerAccount;
    }

    public void setSellerAccount(String sellerAccount) {
        this.sellerAccount = sellerAccount;
    }

    public String getSellerPassword() {
        return sellerPassword;
    }

    public void setSellerPassword(String sellerPassword) {
        this.sellerPassword = sellerPassword;
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

