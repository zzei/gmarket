package com.cdz.goods.mapper;

import com.cdz.goods.bean.Goods;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsMapper {

    @Select("select * from goods where id = #{id}")
    public Goods getGoods(int id);

    @Select("select * from goods")
    public List<Goods> findGoods();

    @Select("select * from goods where seller_id = #{sellerId}")
    public List<Goods> findGoodsBySeller(int sellerId);

    @Select("select * from goods where state = 1 order by click_num desc limit 5")
    public List<Goods> findGoodsClick();

    @Select("select * from goods where state = 1 order by sell_num desc limit 5")
    public List<Goods> findGoodsSell();

    @Select("select * from goods where state = 1 and goods_name like CONCAT('%',#{goodsName},'%')")
    public List<Goods> findGoodsByName(String goodsName);

    @Select("select * from goods where category = #{category}")
    public List<Goods> findGoodsByCategory(int category);

    @Insert("insert into goods(seller_id,goods_name,category,goods_img,goods_content,click_num,sell_num,stock,price,off,sell_time,create_time,state)" +
            "values (#{sellerId},#{goodsName},#{category},#{goodsImg},#{goodsContent},#{clickNum},#{sellNum},#{stock},#{price},#{off}," +
            "#{sellTime},#{createTime},#{state})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    public Integer save(Goods goods);

    @Update("update goods set goods_name = #{goodsName},goods_img = #{goodsImg},goods_content = #{goodsContent},price = #{price} where id = #{id}")
    public Integer update(Goods goods);

    @Update({"<script>" +
            "<foreach collection=\"goodsList\" item=\"goods\" separator=\";\">" +
            "update goods set sell_time = #{goods.sellTime},set state = #{goods.state}" +
            "where id = #{goods.id}" +
            "</foreach>" +
            "</script>"})
    public Integer updateOnSell(List<Goods> goodsList);

    @Update("update goods set click_num = click_num + 1 where id = #{id}")
    public Integer updateClickNum(int id);

    @Update("update goods set sell_num = sell_num + 1 where id = #{id}")
    public Integer updateSellNum(int id);

    @Update("update goods set stock = #{stock} where id = #{id}")
    public Integer updateStock(Goods goods);

    @Update("update goods set state = #{state} where id = #{id}")
    public Integer updateState(Goods goods);

    @Delete("delete from goods where id = #{id}")
    public Integer delete(int id);

}
