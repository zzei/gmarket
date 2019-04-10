package com.cdz.gmail.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cdz.customer.bean.Customer;
import com.cdz.customer.service.CartService;
import com.cdz.goods.bean.Goods;
import com.cdz.goods.service.GoodsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HelloController {

    @Reference
    GoodsService goodsService;

    @Reference
    CartService cartService;

    @GetMapping("/")
    public String welcome(Model model, HttpSession session){
        //查询畅销榜前五
        List<Goods> sellGoodsList = goodsService.findGoodsSell();

        //查询点击榜前五
        List<Goods> clickGoodslist = goodsService.findGoodsClick();

        //若用户登录查出购物车数量
        Customer customer = (Customer) session.getAttribute("customer");
        if(null != customer){
            model.addAttribute("cartCount",cartService.getCartCount(customer.getId()));
        }

        model.addAttribute("sellHot",sellGoodsList);
        model.addAttribute("clickHot",clickGoodslist);

        return "welcome";
    }
}
