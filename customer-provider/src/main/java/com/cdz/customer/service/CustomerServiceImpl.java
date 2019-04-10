package com.cdz.customer.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.cdz.customer.bean.Customer;
import com.cdz.customer.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    CustomerMapper customerMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public Customer getByAccount(String account) {
        return customerMapper.getByAccount(account);
    }

    @Override
    public Customer login(Customer customer) {
        return customerMapper.getByAccountAndPassword(customer);
    }

    @Override
    public boolean checkCustomerAccount(String account) {
        return customerMapper.getAccountCount(account)==0?true:false;
    }

    @Override
    public boolean checkCustomerName(String name) {
        return customerMapper.getNameCount(name)==0?true:false;
    }

    @Override
    public Customer save(Customer customer) {
        customerMapper.save(customer);
        return customerMapper.getCustomer(customer.getId());
    }

    @Override
    public Integer update(Customer customer) {
        stringRedisTemplate.delete("customer::"+customer.getId());
        return customerMapper.update(customer);
    }

    @Override
    public Integer updateCost(Customer customer) {
        stringRedisTemplate.delete("customer::"+customer.getId());
        return customerMapper.updateCost(customer);
    }

    @Cacheable(value = "customer",key = "#id")
    @Override
    public Customer getCustomer(int id) {
        return customerMapper.getCustomer(id);
    }
}
