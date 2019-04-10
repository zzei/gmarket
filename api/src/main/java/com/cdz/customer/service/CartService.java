package com.cdz.customer.service;

import com.cdz.customer.bean.Cart;

import java.util.List;

public interface CartService {

    public Integer getCartCount(int customerId);

    public List<Cart> findCart(int customerId);

    public Integer save(Cart cart);

    public Integer update(Cart cart);

    public Cart getCart(int id);

    public Integer delete(Cart cart);

}
