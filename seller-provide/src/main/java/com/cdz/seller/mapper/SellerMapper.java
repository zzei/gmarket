package com.cdz.seller.mapper;

import com.cdz.seller.bean.Seller;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellerMapper {

    @Select("select * from seller where seller_account = #{account}")
    public Seller getByAccount(String account);

    @Select("select * from seller where seller_account = #{sellerAccount} and seller_password = #{sellerPassword}")
    public Seller getByAccountAndPassword(Seller seller);

    @Insert("insert into seller(seller_name,seller_level,seller_account,seller_password,create_time,state) values " +
            "(#{sellerName},#{sellerLevel},#{sellerAccount},#{sellerPassword},#{createTime},#{state})")
    public Integer save(Seller seller);

    @Select("select count(*) from seller where seller_account = #{sellerAccount}")
    public Integer getAccountCount(String sellerAccount);

    @Select("select count(*) from seller where seller_name = #{sellerName}")
    public Integer getNameCount(String sellerName);

    @Select("select * from seller where id = #{id}")
    public Seller getById(int id);

    @Select("select * from seller where seller_name like CONCAT('%',#{name},'%'")
    public List<Seller> findSellerByName(String name);
}
