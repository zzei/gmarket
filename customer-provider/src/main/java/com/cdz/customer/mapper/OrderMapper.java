package com.cdz.customer.mapper;

import com.cdz.customer.bean.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {

    @Insert("insert into `order`(customer_id,order_no,price,received,total,address_id,create_time,state) values" +
            "(#{customerId},#{orderNo},#{price},#{received},#{total},#{addressId},#{createTime},#{state})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    public Integer save(Order order);

    @Update("update `order` set state = #{state} where id = #{id}")
    public Integer updateState(Order order);

    @Select("select * from `order` where id = #{id}")
    public Order getOrder(int id);

    @Select("select * from `order` where customer_id = #{customerId}")
    public List<Order> findOrder(int customerId);
}
