package com.cdz.seller.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cdz.goods.bean.Category;
import com.cdz.goods.service.CategoryService;
import com.cdz.seller.bean.Seller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CategoryController {

    @Reference
    CategoryService categoryService;

    @PostMapping("/category")
    public String save(Category category, HttpSession session){
        Seller seller = (Seller) session.getAttribute("seller");
        category.setSellerId(seller.getId());
        //返回保存成功是否标识
        int result = categoryService.save(category);
        return "redirect:goods";
    }

    @ResponseBody
    @RequestMapping("/getCategoryById")
    public String getCategoryById(int id){
        Category category = categoryService.getById(id);
        return category.getValue();
    }

}
