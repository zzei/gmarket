package com.cdz.seller;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableDubbo
@EnableCaching
@MapperScan(value = "com.cdz.seller.mapper")
@SpringBootApplication
public class SellerProvideApplication {

	public static void main(String[] args) {
		SpringApplication.run(SellerProvideApplication.class, args);
	}

}
