package com.cdz.gmail.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cdz.customer.bean.*;
import com.cdz.customer.service.CartService;
import com.cdz.customer.service.OrderDetailService;
import com.cdz.customer.service.OrderService;
import com.cdz.goods.service.GoodsService;
import com.cdz.seller.service.AddressService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class OrderController {

    @Reference
    AddressService addressService;

    @Reference
    OrderService orderService;

    @Reference
    OrderDetailService orderDetailService;

    @Reference
    CartService cartService;

    @GetMapping("/toOrder")
    public String toOrder(Model model, HttpSession session){
        Customer customer = (Customer) session.getAttribute("customer");
        List<Address> addressList = addressService.selectByCustomer(customer.getId());

        double price = 0;
        List<Cart> cartList = cartService.findCart(customer.getId());
        for(Cart cart : cartList){
            price += cart.getAllPrice();
        }

        model.addAttribute("addressList",addressList);
        model.addAttribute("price",price);

        return "orderView";
    }


    @GetMapping("/order")
    public String upOrder(@RequestParam("addressId") int addressId,@RequestParam("price") double price, Model model, HttpSession session){
        //默认结算购物车内所有商品
        Customer customer = (Customer) session.getAttribute("customer");

        //添加订单
        Order order = new Order();
        order.setCustomerId(customer.getId());
        order.setAddressId(addressId);
        order.setPrice(price);
        int orderId = orderService.save(order);
        order.setId(orderId);

        List<Cart> cartList = cartService.findCart(customer.getId());
        for(Cart cart : cartList){
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(orderId);
            orderDetail.setGoodsId(cart.getGoodsId());
//            orderDetail.setUnitPrice(goodsService.getGoods(cart.getGoodsId()).getPrice());
            orderDetail.setTotal(cart.getTotal());
            orderDetail.setPrice(cart.getAllPrice());
            orderDetailService.save(orderDetail);
            //加入订单后从购物车中删除
            cartService.delete(cart);
        }

        model.addAttribute("order",order);
        return "pay";
    }


    @GetMapping("/pay/{id}")
    public String pay(@PathVariable("id") int id,Model model){

        Order order = new Order();
        order.setId(id);
        order.setState(1);
        orderService.updateState(order);

        model.addAttribute("msg","支付成功！");
        return "orderMsg";
    }

    @GetMapping("/cancel/{id}")
    public String cancel(@PathVariable("id") int id,Model model){

        Order order = new Order();
        order.setId(id);
        order.setState(2);
        orderService.updateState(order);

        model.addAttribute("msg","订单已取消！");
        return "orderMsg";
    }

    @GetMapping("/orders")
    public String findOrder(@RequestParam(value = "pageNo",defaultValue = "1")int pageNo, Model model,HttpSession session){
        Customer customer = (Customer) session.getAttribute("customer");
        int pageSize = 5;

        PageInfo<Order> pages = orderService.findOrder(customer.getId(),pageNo,pageSize);

        model.addAttribute("pages",pages);
        return "orders";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id")int id, Model model){

        List<OrderDetail> orderDetailList = orderDetailService.findDetail(id);

        model.addAttribute("orderDetailList",orderDetailList);

        return "details";
    }
}
