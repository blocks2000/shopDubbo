package com.example.userservice;

import api.IUserService;
import bean.TUser;
import com.alibaba.dubbo.config.annotation.Reference;
import com.jiang.pojo.ResultBean;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceApplicationTests {

    @Autowired
    IUserService userService;
    @Test
    void contextLoads() {
        TUser user=new TUser();
        user.setUsername("12345678901");
        user.setPassword("123456");
        ResultBean resultBean=userService.checkLogin(user);
        System.out.println(resultBean.getStatusCode());
    }

}
