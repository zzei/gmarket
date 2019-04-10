package com.cdz.gmail.amqp;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cdz.customer.bean.Cart;
import com.cdz.customer.bean.Customer;
import com.cdz.customer.service.CartService;
import com.cdz.goods.bean.Goods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Received {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Reference
    CartService cartService;

    @RabbitListener(queues = "addCart")
    public void addCart(Cart cart){
//        Cart cart = (Cart) rabbitTemplate.receiveAndConvert("addCart");
        logger.info("------------进入队列添加-----date:"+new Date());

        cartService.save(cart);

        logger.info("-----队列添加完成----date:"+new Date());
    }
}
