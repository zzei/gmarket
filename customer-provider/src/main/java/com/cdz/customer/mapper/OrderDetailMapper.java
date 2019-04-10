package com.cdz.customer.mapper;

import com.cdz.customer.bean.OrderDetail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailMapper {

    @Insert("insert into order_detail(order_id,goods_id,unit_price,total,price,received,send_time,state) values" +
            "(#{orderId},#{goodsId},#{unitPrice},#{total},#{price},#{received},#{sendTime},#{state})")
    public Integer save(OrderDetail orderDetail);

    @Update("update order_detail set state = #{state} where id = #{id}")
    public Integer updateState(OrderDetail orderDetail);

    @Select("select * from order_detail where id = #{id}")
    public OrderDetail getDetail(int id);

    @Select("select * from order_detail where order_id = #{orderId}")
    public List<OrderDetail> findDetail(int orderId);
}
