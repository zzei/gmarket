package com.cdz.seller.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.cdz.goods.bean.Goods;
import com.cdz.seller.bean.Seller;
import com.cdz.seller.mapper.SellerMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Component
@Service
public class SellerServiceImpl implements SellerService{

    @Autowired
    SellerMapper sellerMapper;

    @Cacheable(value = "seller",key = "#id")
    @Override
    public Seller getSeller(int id) {
        return sellerMapper.getById(id);
    }

    @Override
    public Seller getByAccount(String account) {
        return sellerMapper.getByAccount(account);
    }


    @Override
    public Seller login(Seller seller) {
        seller.setSellerPassword(DigestUtils.md5DigestAsHex(seller.getSellerPassword().getBytes()));
        return sellerMapper.getByAccountAndPassword(seller);
    }


    @Override
    public boolean checkSellerAccount(String sellerAccount) {
        return sellerMapper.getAccountCount(sellerAccount)==0?true:false;
    }

    @Override
    public boolean checkSellerName(String sellerName) {
        return sellerMapper.getNameCount(sellerName)==0?true:false;
    }

    @Override
    public Seller save(Seller seller) {

        seller.setSellerLevel(1);
        seller.setCreateTime(new Date());
        seller.setState(1);

        //md5加密
        seller.setSellerPassword(DigestUtils.md5DigestAsHex(seller.getSellerPassword().getBytes()));
        sellerMapper.save(seller);

        return seller;
    }

    @Cacheable(value = "sellerPages",key = "'findName:'+ #name +'/'+#pageNo")
    @Override
    public PageInfo<Seller> findSellerByName(String name, int pageNo, int pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        PageHelper.orderBy("create_time DESC"); //按创建时间排序
        List<Seller> sellerList = sellerMapper.findSellerByName(name);

        PageInfo<Seller> pages = new PageInfo<Seller>(sellerList);


        return pages;
    }
}
