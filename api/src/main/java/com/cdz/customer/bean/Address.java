package com.cdz.customer.bean;

import java.io.Serializable;

public class Address implements Serializable{

    private Integer id;

    private String address;

    private String name;

    private String phone;

    private Integer isDefault;

    private Integer customerId;

    public Address() {
    }

    public Address(Integer id, String address, String name, String phone, Integer isDefault, Integer customerId) {
        this.id = id;
        this.address = address;
        this.name = name;
        this.phone = phone;
        this.isDefault = isDefault;
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", isDefault=" + isDefault +
                ", customerId=" + customerId +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}
