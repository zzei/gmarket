package com.cdz.seller.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cdz.goods.bean.Category;
import com.cdz.goods.bean.Goods;
import com.cdz.goods.service.CategoryService;
import com.cdz.goods.service.GoodsService;
import com.cdz.seller.bean.Seller;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class GoodsController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Reference(timeout = 5000,retries = 0)
    GoodsService goodsService;

    @Reference
    CategoryService categoryService;

    //点击进行商品管理
    @GetMapping("/goods")
    public String toGoods(@RequestParam(value="pageNo",defaultValue = "1")int pageNo, Model model, HttpServletRequest request, HttpSession session){
        logger.info("----进入商品列表-----");
        Seller seller = (Seller) session.getAttribute("seller");
        //默认查询第一页 20条记录
//        PageHelper.startPage(pageNo,5);
//        PageHelper.orderBy("create_time DESC"); //按创建时间排序
        int pageSize = 5;
        //查询该店铺的所有商品（分页）
        PageInfo<Goods> pages = goodsService.findGoodsBySeller(seller.getId(),pageNo,pageSize);


        //查询类别
        List<Category> categories = categoryService.findAll(seller.getId());



        model.addAttribute("pages",pages);
        model.addAttribute("categories",categories);

        return "goods";
    }

    @RequestMapping("/toAddGoods")
    public String toAdd(Model model, HttpSession session){
        logger.info("-----前往商品添加——--");

        Seller seller = (Seller) session.getAttribute("seller");
        List<Category> categories = categoryService.findAll(seller.getId());
        model.addAttribute("categories",categories);

        return "goodsControl";
    }

    //添加商品
    @PostMapping("/goods")
    public String goods(Goods goods){
        logger.info("---开始添加商品---"+goods.toString());

        goodsService.save(goods);

        return "redirect:goods";
    }

    //来到编辑页面
    @GetMapping("/goods/{id}")
    public String goods(@PathVariable("id") Integer id, Model model, HttpSession session){
        logger.info("-----前往商品编辑——--");

        Goods goods = goodsService.getGoods(id);

        Seller seller = (Seller) session.getAttribute("seller");
        List<Category> categories = categoryService.findAll(seller.getId());

        model.addAttribute("categories",categories);
        model.addAttribute("goods",goods);

        return "goodsControl";
    }

    @PutMapping("/goods")
    public String update(Goods goods){
        logger.info("---开始编辑商品---"+goods.toString());

        goodsService.update(goods);

        return "redirect:goods";
    }

    @PostMapping("/publishGoods")
    public String publish(List<Goods> goodsList){
        logger.info("----开始发布商品---");

        goodsService.updateOnSell(goodsList);

        return "redirect:goods";
    }

    @PostMapping("/updateStock")
    public String updateStock(Goods goods){
        logger.info("----开始更新库存---");

        goodsService.updateStock(goods);

        return "redirect:goods";
    }

    @PostMapping("/delGoods/{id}")
    public String delGoods(@PathVariable("id") Integer id){
        logger.info("-----商品"+id+"要被删除了----");
        goodsService.delete(id);

        return "redirect:goods";
    }
}
