package com.example.smsservice;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class SmsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmsServiceApplication.class, args);
    }

}
