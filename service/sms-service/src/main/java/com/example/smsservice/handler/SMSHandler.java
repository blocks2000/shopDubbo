package com.example.smsservice.handler;

import api.ISMS;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SMSHandler {

    @Autowired
    private ISMS isms;


    @RabbitHandler
    @RabbitListener(queues = "sms-code-queue")
    public void processSendCode(Map<String,String> params){
        //拿出消息队列中的消息，然后调用自己的服务进行短信发送
        String identification = params.get("identification");
        String code = params.get("code");
        //调用发送短信的service
        isms.sendCodeMessage(identification,code);
    }
}
