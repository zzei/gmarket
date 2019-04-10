package com.cdz.customer.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.cdz.customer.bean.OrderDetail;
import com.cdz.customer.mapper.OrderDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Service
public class OrderDetailServiceImpl implements OrderDetailService{

    @Autowired
    OrderDetailMapper orderDetailMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public Integer save(OrderDetail orderDetail) {
        orderDetail.setState(0);
        return orderDetailMapper.save(orderDetail);
    }

    @Override
    public Integer updateState(OrderDetail orderDetail) {
        //删除缓存
        stringRedisTemplate.delete("detail::"+orderDetail.getId());
        stringRedisTemplate.delete("detail::"+orderDetail.getOrderId());


        return orderDetailMapper.updateState(orderDetail);
    }

    @Cacheable(value = "detail",key = "#id")
    @Override
    public OrderDetail getDetail(int id) {
        return orderDetailMapper.getDetail(id);
    }

    @Cacheable(value = "details",key = "#orderId")
    @Override
    public List<OrderDetail> findDetail(int orderId) {
        return orderDetailMapper.findDetail(orderId);
    }
}
