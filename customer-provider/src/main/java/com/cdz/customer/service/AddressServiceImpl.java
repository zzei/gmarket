package com.cdz.customer.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.cdz.customer.bean.Address;
import com.cdz.customer.mapper.AddressMapper;
import com.cdz.seller.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Service
public class AddressServiceImpl implements AddressService{

    @Autowired
    AddressMapper addressMapper;

    @Override
    public Integer save(Address address) {
        address.setIsDefault(0);
        return addressMapper.save(address);
    }

    @Override
    public Integer update(Address address) {
        return null;
    }

    @Override
    public Integer delete(int id) {
        return addressMapper.delete(id);
    }

    @Override
    public List<Address> selectByCustomer(int id) {
        return addressMapper.selectByCustomer(id);
    }

    @Override
    public Address getAddress(int id) {
        return addressMapper.getAddress(id);
    }

    @Override
    public Integer setDefault(int id, int customerId) {
        //先把所有地址设为非默认
        addressMapper.update0(customerId);

        //设置选中的地址为默认
        addressMapper.update1(id);

        return 1;
    }
}
