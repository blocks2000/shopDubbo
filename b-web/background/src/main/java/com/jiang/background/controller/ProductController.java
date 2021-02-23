package com.jiang.background.controller;

import VO.ProductVO;
import bean.Product;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.jiang.constant.MQConstant;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import product.IProductService;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@Controller
@RequestMapping("product")
public class ProductController {

    @Reference
    private IProductService productService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/list")
    public String list(Model model){
        List<Product> list=productService.list();
        model.addAttribute("list",list);
        return "product/list";
    }

    //pageIndex-->当前第几页  pageSize---》一页有几条记录
    @GetMapping("page/{pageIndex}/{pageSize}")
    public String page(Model model,
                       @PathVariable("pageIndex") Integer pageIndex,
                       @PathVariable("pageSize") Integer pageSize){
        PageInfo<Product> pageInfo=productService.page(pageIndex,pageSize);
        model.addAttribute("pageInfo",pageInfo);
        return "product/list";
    }

    @GetMapping("add")
    public String add(ProductVO productVO){
        //返回添加后的商品ID
       Long productId= productService.add(productVO);

       //发送一个消息到消息中间件
        rabbitTemplate.convertAndSend(MQConstant.CENTER_PRODUCT_EXCHANGE,"product.add",productId);
        rabbitTemplate.convertAndSend(MQConstant.CENTER_PRODUCT_ITEM_EXCHANGE,"product.add",productId);
       //返回到第一页，进行展示
        return "redirect:/product/page/1/1";
    }

    @RequestMapping("searchEach/{typeId}")
    public String searchEach(@PathVariable("typeId") Long typeId,
                             Model model){
        List<Product> Products = productService.searchByTypeId(typeId);
        model.addAttribute("products",Products);
        return "list";
    }

    @RequestMapping("/search")
    public String showSearch(String productName){
        System.out.println(productName);
        String pName= null;
        try {
            pName = URLEncoder.encode(productName,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("redirect:http://localhost:9092/search/queryByKeyWords4PC/1/10?keyWords="+pName);
        return "redirect:http://localhost:9092/search/queryByKeyWords4PC/1/10?keyWords="+pName;
    }
}
