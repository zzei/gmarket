package com.cdz.customer.service;

import com.cdz.customer.bean.OrderDetail;
import com.sun.tools.corba.se.idl.constExpr.Or;

import java.util.List;

public interface OrderDetailService {

    public Integer save(OrderDetail orderDetail);

    public Integer updateState(OrderDetail orderDetail);

    public OrderDetail getDetail(int id);

    public List<OrderDetail> findDetail(int orderId);
}
