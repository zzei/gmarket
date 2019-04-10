package com.cdz.goods.mapper;

import com.cdz.goods.bean.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryMapper {

    @Insert("insert into category(value,category_id,seller_id) values (#{value},#{categoryId},#{sellerId})")
    public Integer save(Category category);

    @Update("update category set value=#{value},category_id=#{categoryId}")
    public Integer update(Category category);

    @Delete("delete from category where id = #{id}")
    public Integer delete(int id);

    @Select("select * from category where seller_id = #{sellerId}")
    public List<Category> findAll(int sellerId);

    @Select("select * from category where id = #{id}")
    public Category getById(int id);
}
