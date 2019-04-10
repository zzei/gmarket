package com.cdz.customer.mapper;

import com.cdz.customer.bean.Address;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressMapper {

    @Insert("insert into address(address,name,phone,is_default,customer_id) values" +
            "(#{address},#{name},#{phone},#{isDefault},#{customerId})")
    public Integer save(Address address);

    @Update("update address set is_default = 0 where customer_id = #{customerId}")
    public Integer update0(int customerId);

    @Update("update address set is_default = 1 where id = #{id}")
    public Integer update1(int id);

    @Update("update address set is_default = #{isDefault} where set id = #{id}")
    public Integer update(Address address);

    @Delete("delete from address where id = #{id}")
    public Integer delete(int id);

    @Select("select * from address where customer_id = #{customerId}")
    public List<Address> selectByCustomer(int id);

    @Select("select * from address where id = #{id}")
    public Address getAddress(int id);
}
