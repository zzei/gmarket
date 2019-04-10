package com.cdz.seller;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
public class SellerWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SellerWebApplication.class, args);
	}

}
