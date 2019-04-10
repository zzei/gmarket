package com.cdz.gmail;

import com.cdz.customer.bean.Cart;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class GmailWebApplicationTests {

	@Autowired
	RabbitTemplate rabbitTemplate;

	@Autowired
	AmqpAdmin amqpAdmin;


	@Test
	public void init(){
		DirectExchange directExchange = new DirectExchange("testCart.direct");
		amqpAdmin.declareExchange(directExchange);
		Queue queue1 = new Queue("testCart1");
//		Queue queue2 = new Queue("testCart2");
		amqpAdmin.declareQueue(queue1);
//		amqpAdmin.declareQueue(queue2);
		Binding binding1 = new Binding("testCart1",Binding.DestinationType.QUEUE,"testCart.direct","testCart",null);
//		Binding binding2 = new Binding("testCart2",Binding.DestinationType.QUEUE,"testCart.direct","testCart",null);
		amqpAdmin.declareBinding(binding1);
//		amqpAdmin.declareBinding(binding2);
	}

	@Test
	public void contextLoads() {

		for(int i = 1;i<10;i++){
			rabbitTemplate.convertAndSend("testCart.direct","testCart","cart:"+i);
		}
	}

	@RabbitListener(queues = "testCart1")
	public void testReceived1(String cart){
		System.out.println("-----testReceived1------"+cart);
	}

	@RabbitListener(queues = "testCart1")
	public void testReceived2(String cart){
		System.out.println("-----testReceived2------"+cart);
	}

}
