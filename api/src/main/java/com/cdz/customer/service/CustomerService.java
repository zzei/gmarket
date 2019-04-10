package com.cdz.customer.service;

import com.cdz.customer.bean.Customer;

public interface CustomerService {
    public Customer getByAccount(String account);

    public Customer login(Customer customer);

    public boolean checkCustomerAccount(String account);

    public boolean checkCustomerName(String name);

    public Customer save(Customer customer);

    public Integer update(Customer customer);

    public Integer updateCost(Customer customer);

    public Customer getCustomer(int id);
}
