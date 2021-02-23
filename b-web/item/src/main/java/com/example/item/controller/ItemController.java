package com.example.item.controller;

import bean.Product;
import com.alibaba.dubbo.config.annotation.Reference;
import com.jiang.pojo.ResultBean;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import product.IProductService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("item")
public class ItemController {

    @Reference
    private IProductService productService;

    @Autowired
    private Configuration configuration;

    @RequestMapping("createById/{id}")
    public String createById(@PathVariable("id") Long id){
        //先拿到对应id值的商品
        Product product = productService.selectByPrimaryKey(id);
        //采用Freemarker生成对应的商品详情页
        try {
            Template template = configuration.getTemplate("item.ftl");
            //放入数据
            Map<String,Object> data=new HashMap<>();
            data.put("product",product);
            //输出
            //获取到项目运行时的路径，获取到static的路径
//            String serverpath = ResourceUtils.getURL("classpath:static").getPath();
//            StringBuilder path=new StringBuilder(serverpath).append(File.separator).append(id).append(".html");
            FileWriter out=new FileWriter( "D:\\shopDubbo\\b-web\\item\\src\\main\\resources\\templates\\"+id+".html");
            template.process(data,out);
            String index=id.toString();
            return index;
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
            return id.toString();
        }
    }
}
