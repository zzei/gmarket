package com.cdz.goods.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.cdz.goods.bean.Goods;
import com.cdz.goods.mapper.CategoryMapper;
import com.cdz.goods.mapper.GoodsMapper;
import com.cdz.seller.bean.Seller;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@Service(timeout = 5000,retries = 0)
public class GoodsServiceImpl implements GoodsService{

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;


    @CachePut(value = "goods",key = "#id")
    @Override
    public Goods getGoods(int id) {
        logger.info("执行查询商品 id为"+id);
        return goodsMapper.getGoods(id);
    }

    @Override
    public List<Goods> findGoods() {
        return goodsMapper.findGoods();
    }

    @Cacheable(value = "goodsPages",key = "'seller:'+#sellerId +'/'+ #pageNo")
    @Override
    public PageInfo<Goods> findGoodsBySeller(int sellerId, int pageNo, int pageSize){
        logger.info("执行查询商品------ 第"+pageNo+"页");
        PageHelper.startPage(pageNo,pageSize);
        PageHelper.orderBy("create_time DESC"); //按创建时间排序
        List<Goods> goodsList = goodsMapper.findGoodsBySeller(sellerId);

        for(Goods goods:goodsList){
            goods.setCategoryValue(categoryMapper.getById(goods.getCategory()).getValue());
            if(goods.getState()==0)
                goods.setGoodsStateValue("未发布");
            else if(goods.getState()==1)
                goods.setGoodsStateValue("已发布");
            else
                goods.setGoodsStateValue("已下架");
        }

        PageInfo<Goods> pages = new PageInfo<Goods>(goodsList);


        return pages;
    }

    @Cacheable(value = "goodsList",key = "'click'")
    @Override
    public List<Goods> findGoodsClick() {
        return goodsMapper.findGoodsClick();
    }

    @Cacheable(value = "goodsList",key = "'sell'")
    @Override
    public List<Goods> findGoodsSell() {
        return goodsMapper.findGoodsSell();
    }

    @Cacheable(value = "goodsPage",key = "'findName:'+#goodsName +'/'+ #pageNo")
    @Override
    public PageInfo<Goods> findGoodsByName(String goodsName, int pageNo, int pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        PageHelper.orderBy("create_time DESC"); //按创建时间排序
        List<Goods> goodsList = goodsMapper.findGoodsByName(goodsName);

        PageInfo<Goods> pages = new PageInfo<Goods>(goodsList);


        return pages;
    }

    @Override
    public List<Goods> findGoodsByCategory(int category) {
        return goodsMapper.findGoodsByCategory(category);
    }


    @Override
    public Integer save(Goods goods) {
        goods.setClickNum(0);
        goods.setSellNum(0);
        goods.setCreateTime(new Date());
        goods.setState(0);
        return goodsMapper.save(goods);
    }

    @Override
    public Integer update(Goods goods) {
        stringRedisTemplate.delete("goods::"+goods.getId());
        return goodsMapper.update(goods);
    }

    @Override
    public Integer updateOnSell(List<Goods> goodsList) {
        for(Goods goods:goodsList){
            goods.setSellTime(new Date());
            goods.setState(1);
            //删除商品缓存  缓存key为goods::id
            stringRedisTemplate.delete("goods::"+goods.getId());
        }
        return goodsMapper.updateOnSell(goodsList);
    }

    @Override
    public Integer updateClickNum(int id) {
        stringRedisTemplate.delete("goods::"+id);
        stringRedisTemplate.delete("goodsList::click");
        return goodsMapper.updateClickNum(id);
    }

    @Override
    public Integer updateSellNum(int id) {
        stringRedisTemplate.delete("goods::"+id);
        stringRedisTemplate.delete("goodsList::sell");
        return goodsMapper.updateSellNum(id);
    }

    @Override
    public Integer updateStock(Goods goods) {
        stringRedisTemplate.delete("goods::"+goods.getId());
        return goodsMapper.updateStock(goods);
    }

    @Override
    public Integer updateState(Goods goods) {
        stringRedisTemplate.delete("goods::"+goods.getId());
        return goodsMapper.updateState(goods);
    }

    @CacheEvict(value = "goods",key = "#id")
    @Override
    public Integer delete(int id) {
        return goodsMapper.delete(id);
    }
}
