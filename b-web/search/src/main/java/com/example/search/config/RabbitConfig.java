//package com.example.search.config;
//
//
//
//import com.jiang.constant.MQConstant;
//import org.springframework.amqp.core.*;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class RabbitConfig {
//
//    //先声明队列
//    @Bean
//    public Queue initProductSearchQueue(){
//        Queue queue=new Queue(MQConstant.CENTER_PRODUCT_EXCHANGE_SEARCH_QUEUE,true,false,false);
//        return queue;
//    }
//
//    @Bean
//    //声明交换机
//    public TopicExchange initProductExchange(){
//        TopicExchange productExchange=new TopicExchange(MQConstant.CENTER_PRODUCT_EXCHANGE,true,false);
//        return productExchange;
//    }
//
//    //把队列和交换机进行绑定
//    @Bean
//    public Binding productSearchQueueBindExchange(Queue initProductSearchQueue,TopicExchange initProductExchange){
//        return BindingBuilder.bind(initProductSearchQueue).to(initProductExchange).with("product.add");
//    }
//}
