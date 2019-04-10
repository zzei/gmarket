package com.cdz.seller.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cdz.goods.bean.Goods;
import com.cdz.goods.service.GoodsService;
import com.cdz.seller.bean.Seller;
import com.cdz.seller.service.SellerService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class SellerController {

    @Reference
    SellerService sellerService;

    @Reference
    GoodsService goodsService;

    @GetMapping("/")
    public String index(){
        return "login";
    }

    @GetMapping("/main")
    public String main(){

        return "dashboard";
    }

    /**
     * 处理登陆请求
     * @return
     */
    @PostMapping("/login")
    public String login(Seller seller, HttpSession session, HttpServletRequest request){
        //账号密码
        Seller seller_log = sellerService.login(seller);
        if(seller_log != null){
            session.setAttribute("seller",seller_log);
            return "redirect:main";
        }
        request.setAttribute("msg","账号密码错误！");
        return "/login";
    }

    @GetMapping("/reg")
    public String toReg(){

        return "reg";
    }

    @PostMapping("/reg")
    public String reg(Seller seller,HttpSession session, HttpServletRequest request){
        if("".equals(seller.getSellerAccount())){
            request.setAttribute("msg","账号不能为空");
            return "reg";
        }else if("".equals(seller.getSellerPassword())){
            request.setAttribute("msg","密码不能为空");
            return "reg";
        }else if("".equals(seller.getSellerName())){
            request.setAttribute("msg","店铺名不能为空");
            return "reg";
        }else if(!sellerService.checkSellerAccount(seller.getSellerAccount())){
            request.setAttribute("msg","账号已被注册");
            return "reg";
        }else if(!sellerService.checkSellerName(seller.getSellerName())){
            request.setAttribute("msg","店铺名已被使用");
            return "reg";
        }else {
            Seller seller_reg = sellerService.save(seller);
            session.setAttribute("seller",seller);
            return "redirect:main";
        }
    }



}
