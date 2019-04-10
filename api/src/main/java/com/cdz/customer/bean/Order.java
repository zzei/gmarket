package com.cdz.customer.bean;

import com.cdz.common.bean.ShowEntity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Order extends ShowEntity implements Serializable{

    private Integer id;

    private Integer customerId;

    private String orderNo;

    private Double price;

    private Double received;

    private Integer total;

    private Integer addressId;

    private Date createTime;

    private Integer state;

    private List<OrderDetail> details;

    public Order() {
    }

    public Order(Integer id, Integer customerId, String orderNo, Double price, Double received, Integer total, Integer addressId, Date createTime, Integer state) {
        this.id = id;
        this.customerId = customerId;
        this.orderNo = orderNo;
        this.price = price;
        this.received = received;
        this.total = total;
        this.addressId = addressId;
        this.createTime = createTime;
        this.state = state;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerId=" + customerId +
                ",orderNo=" + orderNo +
                ", price=" + price +
                ", received=" + received +
                ", total=" + total +
                ", addressId=" + addressId +
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

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
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

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
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

    public List<OrderDetail> getDetails() {
        return details;
    }

    public void setDetails(List<OrderDetail> details) {
        this.details = details;
    }
}
