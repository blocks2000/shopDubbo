package com.example.searchservice.service;

import api.ISearchService;
import bean.Product;
import com.alibaba.dubbo.config.annotation.Service;
import com.jiang.pojo.PageResultBean;
import com.jiang.pojo.ResultBean;
import mapper.ProductMapper;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Component
public class SearchServiceImpl implements ISearchService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private SolrClient solrClient;

    @Override
    public ResultBean synAllData() {
        //查询数据源
        List<Product> list = productMapper.list();
        //MYSQL--->solr
        for (Product product : list) {
            //将每一个product变为document
            //获得solrDocument对象
            SolrInputDocument document = new SolrInputDocument();
            //设置相关属性的值
            document.setField("id", product.getId());
            document.setField("product_name", product.getName());
            document.setField("product_price", product.getPrice());
            document.setField("product_sale_point", product.getSalePoint());
            document.setField("product_images", product.getImage());
            //保存
            try {
                solrClient.add(document);
            } catch (SolrServerException | IOException e) {
                e.printStackTrace();
                return new ResultBean("500", "同步数据失败");
            }
        }
        try {
            solrClient.commit();
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
            return new ResultBean("500", "同步数据失败");
        }
        return new ResultBean("200", "同步数据成功");
    }

    @Override
    public ResultBean synById(Long id) {
        //查询数据源
        Product product = productMapper.selectByPrimaryKey(id);
        //MYSQL--->solr


        //获得solrDocument对象
        SolrInputDocument document = new SolrInputDocument();
        //设置相关属性的值
        document.setField("id", product.getId());
        document.setField("product_name", product.getName());
        document.setField("product_price", product.getPrice());
        document.setField("product_sale_point", product.getSalePoint());
        document.setField("product_images", product.getImage());
        //保存
        try {
            solrClient.add(document);
            solrClient.commit();
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
            return new ResultBean("500", "同步数据失败");
        }
        return new ResultBean("200", "同步数据成功");
    }

    @Override
    public ResultBean delById(Long id) {
        try {
            solrClient.deleteById(String.valueOf(id));
            solrClient.commit();
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
            return new ResultBean("500", "同步数据失败");
        }
        return new ResultBean("200", "同步数据成功");
    }

    @Override
    public ResultBean queryByKeywords(String keywords) {
        //封装查询条件
        List<Product> list = null;
        SolrQuery query = new SolrQuery();
        if (keywords == null || "".equals(keywords.trim())) {
            query.setQuery("product_name:小米9");
        } else {
            query.setQuery("product_name:" + keywords);
        }

        //ADD1 添加高亮的设置
        query.setHighlight(true);
        query.addHighlightField("product_name");
        //queryCondition.addHighlightField("product_sale_point");
        query.setHighlightSimplePre("<font color='red'>");
        query.setHighlightSimplePost("</font>");
        //获取一堆document，将结果变为Product对象，并封装为list返回
        try {
            QueryResponse response = solrClient.query(query);
            SolrDocumentList results = response.getResults();
            list = new ArrayList<>(results.size());

            //ADD2 获取高亮的信息
            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
            //华为
            //1001-华为1 1002-华为2
            //外层Map key（String）--1001
            //外层Map value---1001记录对应的高亮信息(包含多个字段的高亮信息)

            //里层Map key（String）---字段名（product_name）
            //里层Map value----字段对应的高亮信息
            //results--->list
            for (SolrDocument document : results) {
                Product product = new Product();
                product.setId(Long.parseLong(document.getFieldValue("id").toString()));
                product.setName((String) document.getFieldValue("product_name"));
                product.setPrice(Long.parseLong(document.getFieldValue("product_price").toString()));
                product.setImage((String) document.getFieldValue("product_images"));
                //添加进列表

                //设置product_name高亮信息
                //1.获取当前记录的高亮信息
                Map<String, List<String>> higlight = highlighting.get(document.getFieldValue("id").toString());
                //2.获取字段对应的高亮信息
                List<String> productNameHiglight = higlight.get("product_name");
                //3.判断该字段是否有高亮信息
                if (productNameHiglight != null && productNameHiglight.size() > 0) {
                    //高亮信息
                    product.setName(productNameHiglight.get(0));
                } else {
                    //普通的文本信息
                    product.setName(document.getFieldValue("product_name").toString());
                }
                list.add(product);
            }
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
            return new ResultBean("500", null);
        }
        return new ResultBean("200", list);
    }

    //有分页信息的查询
    @Override
    public ResultBean queryByKeywords(String keywords, Integer pageIndex, Integer pageSize) {
        //封装查询条件
        List<Product> list = null;
        long total=0L;
        SolrQuery query = new SolrQuery();
        if (keywords == null || "".equals(keywords.trim())) {
            query.setQuery("product_name:小米9");
        } else {
            query.setQuery("product_name:" + keywords);
        }

        //ADD1 添加高亮的设置
        query.setHighlight(true);
        query.addHighlightField("product_name");
        //queryCondition.addHighlightField("product_sale_point");
        query.setHighlightSimplePre("<font color='red'>");
        query.setHighlightSimplePost("</font>");

        //添加分页信息，要从哪个下标开始
        query.setStart((pageIndex-1)*pageSize);
        //需要多少数据
        query.setRows(pageSize);
        //list--->pageResultBean
        PageResultBean<Product> pageResultBean=new PageResultBean<>();

        //获取一堆document，将结果变为Product对象，并封装为list返回
        try {
            QueryResponse response = solrClient.query(query);
            SolrDocumentList results = response.getResults();
            list = new ArrayList<>(results.size());

            total = results.getNumFound();

            //ADD2 获取高亮的信息
            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
            //华为
            //1001-华为1 1002-华为2
            //外层Map key（String）--1001
            //外层Map value---1001记录对应的高亮信息(包含多个字段的高亮信息)

            //里层Map key（String）---字段名（product_name）
            //里层Map value----字段对应的高亮信息
            //results--->list
            for (SolrDocument document : results) {
                Product product = new Product();
                product.setId(Long.parseLong(document.getFieldValue("id").toString()));
                product.setName((String) document.getFieldValue("product_name"));
                product.setPrice(Long.parseLong(document.getFieldValue("product_price").toString()));
                product.setImage((String) document.getFieldValue("product_images"));
                //添加进列表

                //设置product_name高亮信息
                //1.获取当前记录的高亮信息
                Map<String, List<String>> higlight = highlighting.get(document.getFieldValue("id").toString());
                //2.获取字段对应的高亮信息
                List<String> productNameHiglight = higlight.get("product_name");
                //3.判断该字段是否有高亮信息
                if (productNameHiglight != null && productNameHiglight.size() > 0) {
                    //高亮信息
                    product.setName(productNameHiglight.get(0));
                } else {
                    //普通的文本信息
                    product.setName(document.getFieldValue("product_name").toString());
                }
                list.add(product);
            }
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
            return new ResultBean("500", null);
        }

        //给pageResultBean赋值
        pageResultBean.setList(list);
        pageResultBean.setPageNum(3);
        pageResultBean.setPageSize(pageSize);
        pageResultBean.setPageNum(pageIndex);
        pageResultBean.setTotal(total);
        pageResultBean.setPages((int) (total%pageSize==0?total/pageSize:(total/pageSize)+1));

        return new ResultBean("200", pageResultBean);
    }
}
