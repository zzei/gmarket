package com.cdz.goods.bean;

import com.cdz.common.bean.ShowEntity;

import java.io.Serializable;
import java.util.Date;

public class Goods extends ShowEntity implements Serializable{

    private Integer id;

    private Integer sellerId;

    private String goodsName;

    private Integer category;

    private String goodsImg;

    private String goodsContent;

    private Integer clickNum;

    private Integer sellNum;

    private Integer stock;

    private Double price;

    private Double off;

    private Date sellTime;

    private Date createTime;

    private  Integer state;

    public Goods() {
    }

    public Goods(Integer id, Integer sellerId, String goodsName, Integer category, String goodsImg, String goodsContent, Integer clickNum, Integer sellNum, Integer stock, Double price, Double off, Date sellTime, Date createTime, Integer state) {
        this.id = id;
        this.sellerId = sellerId;
        this.goodsName = goodsName;
        this.category = category;
        this.goodsImg = goodsImg;
        this.goodsContent = goodsContent;
        this.clickNum = clickNum;
        this.sellNum = sellNum;
        this.stock = stock;
        this.price = price;
        this.off = off;
        this.sellTime = sellTime;
        this.createTime = createTime;
        this.state = state;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", sellerId=" + sellerId +
                ", goodsName='" + goodsName + '\'' +
                ", category=" + category +
                ", goodsImg='" + goodsImg + '\'' +
                ", goodsContent='" + goodsContent + '\'' +
                ", clickNum=" + clickNum +
                ", sellNum=" + sellNum +
                ", stock=" + stock +
                ", price=" + price +
                ", off=" + off +
                ", sellTime=" + sellTime +
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

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    public String getGoodsContent() {
        return goodsContent;
    }

    public void setGoodsContent(String goodsContent) {
        this.goodsContent = goodsContent;
    }

    public Integer getClickNum() {
        return clickNum;
    }

    public void setClickNum(Integer clickNum) {
        this.clickNum = clickNum;
    }

    public Integer getSellNum() {
        return sellNum;
    }

    public void setSellNum(Integer sellNum) {
        this.sellNum = sellNum;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getOff() {
        return off;
    }

    public void setOff(Double off) {
        this.off = off;
    }

    public Date getSellTime() {
        return sellTime;
    }

    public void setSellTime(Date sellTime) {
        this.sellTime = sellTime;
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

