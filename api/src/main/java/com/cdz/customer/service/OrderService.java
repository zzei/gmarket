package com.cdz.customer.service;

import com.cdz.customer.bean.Order;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface OrderService {

    public Integer save(Order order);

    public Integer updateState(Order order);

    public Order getOrder(int id);

    public List<Order> findOrder(int customerId);

    PageInfo<Order> findOrder(Integer id, int pageNo, int pageSize);
}
