package com.jiang.productservice.config;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

//通过注解方式来代替xml方式实现pageHelper
@Configuration
public class commonConfig {

    @Bean
    public PageHelper getPageHelper(){
        PageHelper pageHelper=new PageHelper();
        Properties properties=new Properties();
        //设置属性
        properties.setProperty("dialect","mysql");
        properties.setProperty("reasonable","true");
        pageHelper.setProperties(properties);
        return pageHelper;
    }
}
