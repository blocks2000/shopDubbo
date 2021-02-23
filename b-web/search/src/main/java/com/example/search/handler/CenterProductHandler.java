package com.example.search.handler;

import api.ISearchService;
import com.alibaba.dubbo.config.annotation.Reference;
import com.jiang.constant.MQConstant;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
@RabbitListener(queues = MQConstant.CENTER_PRODUCT_EXCHANGE_SEARCH_QUEUE)
public class CenterProductHandler {

    //声明队列
    //绑定交换机  都是通过界面管理平台进行操作
    @Reference
    private ISearchService searchService;

    @RabbitHandler
    public void process(Long newId){
        searchService.synById(newId);
    }
}
