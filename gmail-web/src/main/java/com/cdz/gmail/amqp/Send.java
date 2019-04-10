package com.cdz.gmail.amqp;

import com.cdz.customer.bean.Cart;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class Send {

    @Autowired
    RabbitTemplate rabbitTemplate;

    public void addCart(Cart cart) {
        rabbitTemplate.convertAndSend("addCart",cart);
    }
}
