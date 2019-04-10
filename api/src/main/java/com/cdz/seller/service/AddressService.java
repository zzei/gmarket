package com.cdz.seller.service;

import com.cdz.customer.bean.Address;

import java.util.List;

public interface AddressService {

    public Integer save(Address address);

    public Integer update(Address address);

    public Integer delete(int id);

    public List<Address> selectByCustomer(int id);
    
    public Address getAddress(int id);

    public Integer setDefault(int id, int customerId);
}
