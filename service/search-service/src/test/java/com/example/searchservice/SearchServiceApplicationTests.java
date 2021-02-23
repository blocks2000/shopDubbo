package com.example.searchservice;

import api.ISearchService;
import bean.Product;
import com.jiang.pojo.ResultBean;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@MapperScan("mapper")
class SearchServiceApplicationTests {

    @Autowired
    private ISearchService searchService;

    @Test
    public void synAll(){
        ResultBean resultBean=searchService.synAllData();
        System.out.println(resultBean);
    }

    @Test
    public void synId(){
        searchService.synById(10L);
    }

    @Test
    public void query(){
        ResultBean resultBean=searchService.queryByKeywords("小米");
        List<Product> list= (List<Product>) resultBean.getData();
        for (Product product : list) {
            System.out.printf(product.getName());
        }
    }
    @Test
    void contextLoads() {
        System.out.printf("aaa");
    }

}
