package com.example.item.handler;

import bean.Product;
import com.alibaba.dubbo.config.annotation.Reference;
import com.jiang.constant.MQConstant;
import com.jiang.pojo.ResultBean;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import product.IProductService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RabbitListener(queues = MQConstant.CENTER_ITEM_QUEUE)
public class CenterProductHandler {

    //声明队列
    //绑定交换机  都是通过界面管理平台进行操作
    @Reference
    private IProductService productService;
    @Autowired
    private Configuration configuration;

    @RabbitHandler
    public void process(Long id){
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
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }
}
