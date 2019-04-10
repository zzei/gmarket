package com.cdz.goods.service;

import com.cdz.goods.bean.Category;

import java.util.List;

public interface CategoryService {

    public Integer save(Category category);

    public Integer update(Category category);

    public Integer delete(int id);

    public List<Category> findAll(int sellerId);

    public Category getById(int id);
}
