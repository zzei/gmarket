package com.cdz.customer.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.cdz.customer.bean.Order;
import com.cdz.customer.mapper.OrderMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.tools.corba.se.idl.constExpr.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public Integer save(Order order) {
        //生成订单编号
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");

        String orderNo = "G"+order.getCustomerId()+order.getAddressId()+"666"+ sdf.format(d);

        order.setOrderNo(orderNo);
        order.setCreateTime(d);
        order.setState(0);
        orderMapper.save(order);
        //返回订单id
        return order.getId();
    }

    @Override
    public Integer updateState(Order order) {
        //删除现有缓存
        stringRedisTemplate.delete("order::"+order.getId());
        stringRedisTemplate.delete("orders::"+order.getCustomerId());

        return orderMapper.updateState(order);
    }

    @Cacheable(value = "order",key = "#id")
    @Override
    public Order getOrder(int id) {
        return orderMapper.getOrder(id);
    }


//    @Cacheable(value = "orders",key = "#customerId")
    @Override
    public List<Order> findOrder(int customerId) {
        return orderMapper.findOrder(customerId);
    }

//    @Cacheable(value = "orderPages",key = "#customerId+'/'+#pageNo")
    @Override
    public PageInfo<Order> findOrder(Integer customerId, int pageNo, int pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        PageHelper.orderBy("create_time DESC"); //按创建时间排序
        List<Order> orderList = orderMapper.findOrder(customerId);

        PageInfo<Order> pages = new PageInfo<Order>(orderList);

        return pages;
    }
}
