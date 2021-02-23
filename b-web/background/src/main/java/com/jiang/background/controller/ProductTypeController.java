package com.jiang.background.controller;

import bean.Product_type;
import com.alibaba.dubbo.config.annotation.Reference;
import com.jiang.base.IBaseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import product.IProductTypeService;

import java.util.List;

@RequestMapping("productType")
@RestController
public class ProductTypeController {

    @Reference
    private IProductTypeService productTypeService;

    @GetMapping("list")
    public List<Product_type> list(){
        return productTypeService.list();
    }
}
