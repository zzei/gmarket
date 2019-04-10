package com.cdz.customer;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@EnableDubbo
@MapperScan("com.cdz.customer.mapper")
@SpringBootApplication
public class CustomerProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerProviderApplication.class, args);
	}

}
