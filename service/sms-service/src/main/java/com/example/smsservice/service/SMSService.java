package com.example.smsservice.service;

import api.ISMS;
import com.alibaba.dubbo.config.annotation.Service;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import pojo.SMSResponse;

@Service
public class SMSService implements ISMS {
    @Override
    public SMSResponse sendCodeMessage(String phone, String code) {
        //身份认证---》类似于 accountID和authToken
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "<accessKeyId>", "<accessSecret>");
        IAcsClient client = new DefaultAcsClient(profile);

        //固定配置，将请求参数组好
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        //手机号
        request.putQueryParameter("PhoneNumbers", phone);
        //模板信息
        request.putQueryParameter("SignName", "兄弟技术联盟");
        request.putQueryParameter("TemplateCode", "SMS_177246089");
        request.putQueryParameter("TemplateParam", "{\"code\":\""+code+"\"}");
        try {
            //发送请求，获得结果
            CommonResponse response = client.getCommonResponse(request);
//            //将返回结果的string转换为一个对象
//            ObjectMapper objectMapper=new ObjectMapper();
            Gson gson=new Gson();
            return gson.fromJson(response.getData(),SMSResponse.class);
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return null;
    }
}
