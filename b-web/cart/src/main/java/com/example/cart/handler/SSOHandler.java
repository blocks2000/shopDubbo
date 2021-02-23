package com.example.cart.handler;

import api.ICartService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SSOHandler {

    @Reference
    private ICartService cartService;

    @RabbitHandler
    @RabbitListener(queues = "sso-queue-cart")
    public void process(Map<String,String> params){
        String nologinKey = params.get("nologinKey");
        String loginKey = params.get("loginKey");
        //合并购物车
        cartService.merge("user:cart:"+nologinKey,"user:cart:"+loginKey);
    }
}
