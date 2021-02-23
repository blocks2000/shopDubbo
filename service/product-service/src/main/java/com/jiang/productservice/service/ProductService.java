package com.jiang.productservice.service;

import VO.ProductVO;
import bean.Product;
import bean.Product_desc;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiang.base.BaseServiceImpl;
import com.jiang.base.IBaseDao;
import mapper.ProductMapper;
import mapper.Product_descMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import product.IProductService;

import java.util.Date;
import java.util.List;

@Service
@Component
public class ProductService extends BaseServiceImpl<Product> implements IProductService {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private Product_descMapper product_descMapper;
    @Override
    public IBaseDao<Product> getBaseDao() {
        return productMapper;
    }

    @Override
    public PageInfo<Product> page(Integer pageIndex, Integer pageSize) {
        PageHelper.startPage(pageIndex,pageSize);
        List<Product> list=productMapper.list();
        PageInfo<Product> pageInfo=new PageInfo<>(list,3);
        return pageInfo;
    }

    @Override
    public Long add(ProductVO productVO) {
        //添加商品的基本信息
        Product product=productVO.getProduct();
        //设置常规属性的值
        product.setFlag(true);
        product.setCreateTime(new Date());
        product.setUpdateTime(product.getCreateTime());
        product.setCreateUser(1L);
        product.setUpdateUser(1L);

        productMapper.insertSelective(product);
        //添加商品的描述信息
        Product_desc desc=new Product_desc();
        desc.setProductId(product.getId());
        desc.setpDesc(productVO.getProductDesc());
        product_descMapper.insertSelective(desc);
        //返回商品id
        return product.getId();
    }

    @Override
    public List<Product> searchByTypeId(Long TypeId) {
        return productMapper.searchByTypeId(TypeId);
    }
}
