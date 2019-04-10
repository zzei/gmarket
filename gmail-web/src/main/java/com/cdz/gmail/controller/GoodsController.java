package com.cdz.gmail.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cdz.goods.bean.Goods;
import com.cdz.goods.service.GoodsService;
import com.cdz.seller.service.SellerService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GoodsController {

    @Reference(timeout = 5000,retries = 0)
    GoodsService goodsService;

    @Reference
    SellerService sellerService;

    //点击进入商品页面
    @GetMapping("/goods/{id}")
    public String toGoods(@PathVariable("id") int id, Model model){
        //商品点击量增加
        goodsService.updateClickNum(id);

        Goods goods = goodsService.getGoods(id);
        model.addAttribute("goods",goods);
        return "goods";
    }

    @GetMapping("/searchGoods/{search}")
    public String searchGoods(@PathVariable("search") String search, @RequestParam(value="pageNo",defaultValue = "1")int pageNo, Model model){
        int pageSize = 5;

        PageInfo<Goods> pages = goodsService.findGoodsByName(search,pageNo,pageSize);

        for(Goods goods:pages.getList()){
            goods.setSellerValue(sellerService.getSeller(goods.getSellerId()).getSellerName());
        }

        model.addAttribute("search",search);
        model.addAttribute("pages",pages);
        return "goodslist";
    }
}
