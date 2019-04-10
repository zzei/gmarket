package com.cdz.gmail.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cdz.customer.bean.Address;
import com.cdz.customer.bean.Cart;
import com.cdz.customer.bean.Customer;
import com.cdz.customer.service.CartService;
import com.cdz.customer.service.CustomerService;
import com.cdz.gmail.amqp.Send;
import com.cdz.goods.bean.Goods;
import com.cdz.goods.service.GoodsService;
import com.cdz.seller.bean.Seller;
import com.cdz.seller.service.AddressService;
import com.cdz.seller.service.SellerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Reference
    CustomerService customerService;

    @Reference
    CartService cartService;

    @Reference
    GoodsService goodsService;

    @Reference
    SellerService sellerService;


    @Autowired
    Send send;

    @GetMapping("/reg")
    public String toReg(){

        return "reg";
    }

    /**
     * 处理登陆请求
     * @return
     */
    @PostMapping("/login")
    public String login(Customer customer, HttpSession session, HttpServletRequest request){
        //账号密码
        Customer customer_log = customerService.login(customer);
        if(customer_log != null){
            session.setAttribute("customer",customer_log);
            return "redirect:/";
        }
        request.setAttribute("msg","账号密码错误！");
        return "/";
    }

    @PostMapping("/reg")
    public String reg(Customer customer, HttpServletRequest request, HttpSession session){
        if("".equals(customer.getCustomerAccount())){
            request.setAttribute("msg","账号不能为空");
            return "reg";
        }else if("".equals(customer.getCustomerPassword())){
            request.setAttribute("msg","密码不能为空");
            return "reg";
        }else if("".equals(customer.getCustomerName())){
            request.setAttribute("msg","昵称不能为空");
            return "reg";
        }else if(!customerService.checkCustomerAccount(customer.getCustomerAccount())){
            request.setAttribute("msg","账号已被注册");
            return "reg";
        }else if(!customerService.checkCustomerName(customer.getCustomerName())){
            request.setAttribute("msg","昵称已被使用");
            return "reg";
        }else {
            Customer customer_reg = customerService.save(customer);

            session.setAttribute("customer",customer_reg);
            return "redirect:/";
        }
    }

    @GetMapping("/main")
    public String main(){

        return "main";
    }


    @GetMapping("/toCart")
    public String toCart(Model model, HttpSession session){

        Customer customer = (Customer) session.getAttribute("customer");
        //查询所有购物车中所有商品
        List<Cart> cartList = cartService.findCart(customer.getId());
        for (Cart cart_in : cartList){
            Goods goods = goodsService.getGoods(cart_in.getGoodsId());
            cart_in.setGoodsValue(goods.getGoodsName());
            cart_in.setSellerValue(sellerService.getSeller(goods.getSellerId()).getSellerName());
        }

        model.addAttribute("cartList",cartList);

        return "carts";
    }

    @GetMapping("/addCart/{id}")
    public String addCart(@PathVariable("id") int id, Model model, HttpSession session){

        Customer customer = (Customer) session.getAttribute("customer");
        Goods goods = goodsService.getGoods(id);
        Cart cart = new Cart();
        cart.setCustomerId(customer.getId());
        //默认第一次加入一件商品
        cart.setGoodsId(id);
        cart.setInPrice(goods.getPrice());
        cart.setTotal(1);
        cart.setAllPrice(goods.getPrice());

        //发送消息队列
        send.addCart(cart);
        //cartService.save(cart);

        model.addAttribute("goods",goods);
        return "cartSuccess";
//        logger.info("------准备进入购物车列表-----date:"+new Date());
//        return "redirect:/customer/toCart";
    }

    @GetMapping("/updateCart/{id}")
    public String updateCart(@PathVariable("id") int id, @RequestParam("goodsId") int goodsId, @RequestParam("total") int total, HttpSession session){

        Customer customer = (Customer) session.getAttribute("customer");
        Cart cart = new Cart();
        cart.setId(id);
        cart.setCustomerId(customer.getId());
        if(total == 0){
            cartService.delete(cart);
        }else{
            Goods goods = goodsService.getGoods(goodsId);

            cart.setTotal(total);
            cart.setAllPrice(goods.getPrice()*total);
            cartService.update(cart);
        }

        return "redirect:/customer/toCart";
    }
}
