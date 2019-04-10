package com.cdz.gmail.config;

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
                        .addPathPatterns("/customer/**")
                        .excludePathPatterns("/customer/reg","/customer/login","/customer/asserts/**");

            }

            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("").setViewName("welcome");
                registry.addViewController("/index").setViewName("welcome");
                registry.addViewController("/index.html").setViewName("welcome");
            }
        };
        return webMvcConfigurer;
    }


}
