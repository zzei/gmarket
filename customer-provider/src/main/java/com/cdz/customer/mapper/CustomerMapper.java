package com.cdz.customer.mapper;

import com.cdz.customer.bean.Customer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerMapper {

    @Select("select * from customer where customer_account=#{acccount}")
    public Customer getByAccount(String account);

    @Select("select * from customer where customer_account=#{customerAccount} and customer_password=#{customerPassword}")
    public Customer getByAccountAndPassword(Customer customer);

    @Select("select count(*) from customer where customer_account=#{account}")
    public Integer getAccountCount(String account);

    @Select("select count(*) from customer where customer_name=#{name}")
    public Integer getNameCount(String name);

    @Insert("insert into customer(customer_account,customer_password,customer_name,cost,create_time,state) values" +
            "(#{customerAccount},#{customerPassword},#{customerName},#{cost},#{createTime},#{state})")
    @Options(useGeneratedKeys = true,keyColumn = "id",keyProperty = "id")
    public Integer save(Customer customer);

    @Update("update customer set customer_img = #{customerImg},customer_content = #{customerContent} where id = #{id}")
    public Integer update(Customer customer);

    @Update("update customer set cost = #{cost} where id = #{id}")
    public Integer updateCost(Customer customer);

    @Select("select * from customer where id = #{id}")
    public Customer getCustomer(int id);

}
