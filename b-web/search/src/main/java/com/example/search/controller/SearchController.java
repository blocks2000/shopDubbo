package com.example.search.controller;

import api.ISearchService;
import bean.Product;
import com.alibaba.dubbo.config.annotation.Reference;
import com.jiang.pojo.PageResultBean;
import com.jiang.pojo.ResultBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("search")
public class SearchController {

    @Reference
    private ISearchService searchService;

    @ResponseBody
    @RequestMapping("synAllData")
    public ResultBean synAll(){
        return searchService.synAllData();
    }


    //客户端异步请求
    @ResponseBody
    @RequestMapping("queryByKeyWords")
    public ResultBean queryByKeyWords(String keyWords){
        ResultBean resultBean = searchService.queryByKeywords(keyWords);
        return resultBean;
    }

    @RequestMapping("queryByKeyWords4PC/{pageIndex}/{pageSize}")
    public String queryByKeyWords4PC(String keyWords, Model model,
                                      @PathVariable("pageIndex") Integer pageIndex,
                                      @PathVariable("pageSize") Integer pageSize){
        ResultBean resultBean = searchService.queryByKeywords(keyWords,pageIndex,pageSize);
        if("200".equals(resultBean.getStatusCode())){
            //List<Product> list= (List<Product>) resultBean.getData();
            PageResultBean<Product> pageResultBean = (PageResultBean<Product>) resultBean.getData();
            model.addAttribute("page",pageResultBean);
        }
        return "list";
    }

    @RequestMapping("/jump")
    public String jump(){
        return "redirect:http://localhost:9098/cart/query";
    }


}
