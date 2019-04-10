package com.cdz.gmail.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cdz.seller.bean.Seller;
import com.cdz.seller.service.SellerService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SellerController {

    @Reference
    SellerService sellerService;

    @GetMapping("/searchSeller/{search}")
    public String searchSeller(@PathVariable("search") String search, @RequestParam(value="pageNo",defaultValue = "1") int pageNo, Model model){

        int pageSize = 5;

        PageInfo<Seller> pages = sellerService.findSellerByName(search,pageNo,pageSize);

        model.addAttribute("search",search);
        model.addAttribute("pages",pages);
        return "sellerlist";
    }
}
