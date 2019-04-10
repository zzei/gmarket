package com.cdz.gmail;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@EnableRabbit
@SpringBootApplication
public class GmailWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(GmailWebApplication.class, args);
	}

}
