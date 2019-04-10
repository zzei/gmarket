package com.cdz.customer.bean;

import com.cdz.common.bean.ShowEntity;

import java.io.Serializable;

public class Cart extends ShowEntity implements Serializable{

    private Integer id;

    private Integer customerId;

    private Integer goodsId;

    private Double inPrice;

    private Integer total;

    private Double allPrice;

    public Cart() {
    }

    public Cart(Integer id, Integer customerId, Integer goodsId, Double inPrice, Integer total, Double allPrice) {
        this.id = id;
        this.customerId = customerId;
        this.goodsId = goodsId;
        this.inPrice = inPrice;
        this.total = total;
        this.allPrice = allPrice;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", goodsId=" + goodsId +
                ", inPrice=" + inPrice +
                ", total=" + total +
                ", allPrice=" + allPrice +
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

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Double getInPrice() {
        return inPrice;
    }

    public void setInPrice(Double inPrice) {
        this.inPrice = inPrice;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Double getAllPrice() {
        return allPrice;
    }

    public void setAllPrice(Double allPrice) {
        this.allPrice = allPrice;
    }
}
