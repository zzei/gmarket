package com.cdz.goods.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.cdz.goods.bean.Category;
import com.cdz.goods.mapper.CategoryMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @HystrixCommand
    @Override
    public Integer save(Category category) {
        return categoryMapper.save(category);
    }

    @HystrixCommand
    @Override
    public Integer update(Category category) {
        stringRedisTemplate.delete("category::"+category.getId());
        return categoryMapper.update(category);
    }

    @HystrixCommand
    @CacheEvict(value = "category",key = "#id")
    @Override
    public Integer delete(int id) {
        return categoryMapper.delete(id);
    }

    @HystrixCommand
    @Cacheable(cacheNames = "categories",key = "#sellerId")
    @Override
    public List<Category> findAll(int sellerId) {
        return categoryMapper.findAll(sellerId);
    }

    @HystrixCommand
    @Cacheable(cacheNames = "category",key = "#id")
    @Override
    public Category getById(int id) {
        return categoryMapper.getById(id);
    }
}
