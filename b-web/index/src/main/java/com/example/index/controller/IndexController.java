package com.example.index.controller;


import bean.Product_type;
import com.alibaba.dubbo.config.annotation.Reference;
import com.jiang.pojo.ResultBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import product.IProductTypeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@Controller
@RequestMapping("index")
public class IndexController {

    @Reference
    private IProductTypeService productTypeService;

    @RequestMapping("show")
    public String showIndex(Model model){
        //获取到数据
        List<Product_type> list=productTypeService.list();
        //将数据封装
        model.addAttribute("list",list);
        return "index";
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


    @RequestMapping("listType")
    @ResponseBody
    public ResultBean listType(){
        //获取到数据
        List<Product_type> list=productTypeService.list();
        //返回封装数据给前端
        return new ResultBean("200",list);
    }
}
