package com.cdz.goods.service;

import com.cdz.goods.bean.Goods;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface GoodsService {

    public Goods getGoods(int id);

    public List<Goods> findGoods();

    public PageInfo<Goods> findGoodsBySeller(int sellerId, int pageNo, int pageSize);

    public List<Goods> findGoodsClick();

    public List<Goods> findGoodsSell();

    public PageInfo<Goods> findGoodsByName(String goodsName, int pageNo, int pageSize);

    public List<Goods> findGoodsByCategory(int category);

    public Integer save(Goods goods);

    public Integer update(Goods goods);

    public Integer updateOnSell(List<Goods> goodsList);

    public Integer updateClickNum(int id);

    public Integer updateSellNum(int id);

    public Integer updateStock(Goods goods);

    public Integer updateState(Goods goods);

    public Integer delete(int id);


}
