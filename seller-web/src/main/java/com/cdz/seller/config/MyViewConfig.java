package com.cdz.seller.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyViewConfig implements WebMvcConfigurer {

    @Bean
    public WebMvcConfigurer getWebMvcConfigurer() {
        WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new MyInterceptor())
                        .addPathPatterns("/**")
                        .excludePathPatterns("/","/index","/index.html","/login","/reg","/login.html","/asserts/**");
            }

            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("").setViewName("login");
                registry.addViewController("/index").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
            }
        };
        return webMvcConfigurer;
    }


}
