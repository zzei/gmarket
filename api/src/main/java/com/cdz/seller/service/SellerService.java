package com.cdz.seller.service;

import com.cdz.seller.bean.Seller;
import com.github.pagehelper.PageInfo;

public interface SellerService {

    public Seller getSeller(int id);

    public Seller getByAccount(String account);

    public Seller login(Seller seller);

    public boolean checkSellerAccount(String account);

    public boolean checkSellerName(String name);

    public Seller save(Seller seller);

    public PageInfo<Seller> findSellerByName(String name,int pageNo,int pageSize);
}
