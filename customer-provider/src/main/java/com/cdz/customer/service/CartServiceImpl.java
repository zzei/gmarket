package com.cdz.customer.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.cdz.customer.bean.Cart;
import com.cdz.customer.mapper.CartMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Service
public class CartServiceImpl implements CartService{

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CartMapper cartMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Cacheable(value = "cartCount",key = "#customerId")
    @Override
    public Integer getCartCount(int customerId) {
        return cartMapper.getCartCount(customerId);
    }

    @Cacheable(value = "carts",key = "#customerId")
    @Override
    public List<Cart> findCart(int customerId) {
        logger.info("----------查询购物车-------");
        List<Cart> carts = cartMapper.findCart(customerId);

        return carts;
    }

    @Override
    public Integer save(Cart cart) {
        //查找购物车中是否有同类商品
        Cart cart_in = cartMapper.getCartByGoods(cart);

        //判断缓存是否存在，存在则删除

        if(stringRedisTemplate.hasKey("carts::"+cart.getCustomerId())){
            stringRedisTemplate.delete("carts::"+cart.getCustomerId());
        }

        if(cart_in != null){
            cart_in.setAllPrice(cart_in.getAllPrice() + cart.getInPrice());
            cart_in.setTotal(cart_in.getTotal() + 1);
            return cartMapper.update(cart_in);
        }else{
            stringRedisTemplate.delete("cartCount::"+cart.getCustomerId());
            return cartMapper.save(cart);
        }
    }

    @Override
    public Integer update(Cart cart) {

        stringRedisTemplate.delete("carts::"+cart.getCustomerId());
        return cartMapper.update(cart);
    }

    @Override
    public Cart getCart(int id) {
        return cartMapper.getCart(id);
    }


    @Override
    public Integer delete(Cart cart) {
        //删除购物车缓存
        if(stringRedisTemplate.hasKey("carts::"+cart.getCustomerId())){
            stringRedisTemplate.delete("carts::"+cart.getCustomerId());
        }
        if(stringRedisTemplate.hasKey("cartCount::"+cart.getCustomerId())){
            stringRedisTemplate.delete("cartCount::"+cart.getCustomerId());
        }
        return cartMapper.delete(cart.getId());
    }
}
