package com.example.cartservice;

import api.ICartService;
import com.jiang.pojo.ResultBean;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CartServiceApplicationTests {
    @Autowired
    private ICartService cartService;

    @Test
    void contextLoads() {
    }

}
