package com.jiang.productservice.service;

import bean.Product_type;
import com.alibaba.dubbo.config.annotation.Service;
import com.jiang.base.BaseServiceImpl;
import com.jiang.base.IBaseDao;
import mapper.Product_typeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import product.IProductTypeService;

import javax.annotation.Resource;
import java.util.List;

@Service
@Component
public class ProductTypeService extends BaseServiceImpl<Product_type> implements IProductTypeService {

    @Autowired
   private Product_typeMapper product_typeMapper;

    @Resource(name = "myStringRedisTemplate")
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public IBaseDao<Product_type> getBaseDao() {
        return product_typeMapper;
    }

    //重写获取列表的方法
    public List<Product_type> list(){
        //查询当前缓存是否有需要查询的信息
        List<Product_type> list = (List<Product_type>) redisTemplate.opsForValue().get("productType:list");
        if (list==null||list.size()==0){
            //用数据库查询的方式查出列表信息
            list = super.list();
            //如果当前缓存没有要查询的信息，则把信息加入缓存
            redisTemplate.opsForValue().set("productType:list",list);
            System.out.println("数据库");

        }
        //System.out.println("redis");
        return list;
    }

}
