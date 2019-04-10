package com.cdz.customer.bean;

import com.cdz.common.bean.ShowEntity;

import java.io.Serializable;
import java.util.Date;

public class OrderDetail extends ShowEntity implements Serializable{

    private Integer id;

    private Integer orderId;

    private Integer goodsId;

    private Double unitPrice;

    private Integer total;

    private Double price;

    private Double received;

    private Date sendTime;

    private Integer state;

    public OrderDetail() {
    }

    public OrderDetail(Integer id, Integer orderId, Integer goodsId, Double unitPrice, Integer total, Double price, Double received, Date sendTime, Integer state) {
        this.id = id;
        this.orderId = orderId;
        this.goodsId = goodsId;
        this.unitPrice = unitPrice;
        this.total = total;
        this.price = price;
        this.received = received;
        this.sendTime = sendTime;
        this.state = state;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", goodsId=" + goodsId +
                ", unitPrice=" + unitPrice +
                ", total=" + total +
                ", price=" + price +
                ", received=" + received +
                ", sendTime=" + sendTime +
                ", state=" + state +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getReceived() {
        return received;
    }

    public void setReceived(Double received) {
        this.received = received;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
