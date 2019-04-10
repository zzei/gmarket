package com.cdz.gmail.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cdz.customer.bean.Address;
import com.cdz.customer.bean.Customer;
import com.cdz.seller.service.AddressService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class AddressController {

    @Reference
    AddressService addressService;

    @GetMapping("/address")
    public String address(Model model, HttpSession session){
        Customer customer = (Customer) session.getAttribute("customer");
        List<Address> addressList = addressService.selectByCustomer(customer.getId());

        model.addAttribute("addressList",addressList);
        return "address";
    }

    @GetMapping("/toAddAddress")
    public String toAddAddress(){

        return "addressControl";
    }

    @PostMapping("/address")
    public String saveAddress(Address address){
        addressService.save(address);
        return "redirect:/customer/address";
    }

    @GetMapping("/address/{id}")
    public String toUpdateAddress(@PathVariable("id") int id,Model model){
        model.addAttribute("address",addressService.getAddress(id));
        return "addressControl";
    }

    @PutMapping("/address")
    public String update(Address address){

        addressService.update(address);

        return "redirect:/customer/address";
    }

    @GetMapping("/setDefault/{id}")
    public String setDefault(@PathVariable("id") int id, HttpSession session){
        Customer customer = (Customer) session.getAttribute("customer");

        addressService.setDefault(id,customer.getId());

        return "redirect:/customer/address";
    }

    @GetMapping("/delAddress/{id}")
    public String delAddress(@PathVariable("id") int id){
        addressService.delete(id);
        return "redirect:/customer/address";
    }

}
