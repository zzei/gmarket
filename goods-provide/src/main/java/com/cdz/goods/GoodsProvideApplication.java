package com.cdz.goods;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@EnableCaching
@EnableDubbo
@EnableHystrix
@MapperScan(value = "com.cdz.goods.mapper")
@SpringBootApplication
public class GoodsProvideApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoodsProvideApplication.class, args);
	}

}
