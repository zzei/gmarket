package com.cdz.seller.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druid(){

        return new DruidDataSource();
    }

    //配置管理后台的Servlet
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        Map<String,String> params = new HashMap<String,String>();
        params.put("loginUsername","admin");
        params.put("loginPassword","123456");
        params.put("deny","10.211.55.7");
        bean.setInitParameters(params);
        return bean;
    }

    //配置filter
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());

        Map<String,String> params = new HashMap<String,String>();
        params.put("exclusions","*.js,*.css,/druid/*");
        bean.setInitParameters(params);
        bean.setUrlPatterns(Arrays.asList("/*"));

        return bean;
    }
}
