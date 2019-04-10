package com.cdz.customer.mapper;

import com.cdz.customer.bean.Cart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartMapper {

    @Select("select count(*) from cart where customer_id = #{customerId}")
    public Integer getCartCount(int customerId);

    @Select("select * from cart where customer_id = #{customerId}")
    public List<Cart> findCart(int customerId);

    @Insert("insert into cart(customer_id,goods_id,in_price,total,all_price) values " +
            "(#{customerId},#{goodsId},#{inPrice},#{total},#{allPrice})")
    public Integer save(Cart cart);

    @Update("update cart set total = #{total},all_price = #{allPrice} where id = #{id}")
    public Integer update(Cart cart);

    @Select("select * from cart where id = #{id}")
    public Cart getCart(int id);

    @Select("select * from cart where goods_id = #{goodsId} and customer_id = #{customerId}")
    public Cart getCartByGoods(Cart cart);

    @Delete("delete from cart where id = #{id}")
    public Integer delete(int id);
}
