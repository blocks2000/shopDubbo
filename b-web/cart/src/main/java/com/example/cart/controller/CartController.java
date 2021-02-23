package com.example.cart.controller;

import api.ICartService;
import api.IUserService;
import bean.Product;
import com.alibaba.dubbo.config.annotation.Reference;
import com.jiang.pojo.ResultBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pojo.CartItem;
import product.IProductService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("cart")
public class CartController {

    @Reference
    private ICartService cartService;

    @Reference
    private IUserService userService;

    @Reference
    private IProductService productService;

    @CrossOrigin
    @GetMapping("add/{productId}/{count}")
    public ResultBean add(@PathVariable("productId") long productId,
                          @PathVariable("count") Integer count,
                          @CookieValue(name = "cart_token",required = false) String cartToken,
                          HttpServletRequest request,
                          HttpServletResponse response){

        String userToken = (String) request.getAttribute("user");
        //如果userToken不为空，说明用户已经登录，直接返回
        if (userToken!=null){
            return cartService.add(userToken,productId,count);
        }
        //如果未登录
        if (cartToken==null){
            cartToken=UUID.randomUUID().toString();
        }
        reFlushCookie(cartToken, response);

        return cartService.add(cartToken,productId,count);
    }

    @GetMapping(value = "query")
    public String query(@CookieValue(name = "cart_token",required = false) String cartToken,
                        HttpServletResponse response,
                        HttpServletRequest request, Model model){

        String userToken= (String) request.getAttribute("user");
        if (userToken!=null){
            List<Product> list=new ArrayList<>();
            ResultBean resultBean = cartService.query(userToken);
            List<CartItem> items= (List<CartItem>) resultBean.getData();
            for (CartItem cartItem : items) {
                Long productId=cartItem.getProductId();
                Product product=  productService.selectByPrimaryKey(productId);
                list.add(product);
            }
            model.addAttribute("list",list);
            return "cart";

        }
        if (cartToken!=null){
            List<Product> list=new ArrayList<>();
            ResultBean resultBean = cartService.query(cartToken);
            reFlushCookie(cartToken,response);
            List<CartItem> items= (List<CartItem>) resultBean.getData();
            for (CartItem cartItem : items) {
                Long productId=cartItem.getProductId();
                Product product=  productService.selectByPrimaryKey(productId);
                list.add(product);
            }
            model.addAttribute("list",list);
        }
        return "cart";
    }

    @GetMapping("delete/{productId}")
    public ResultBean delete(@PathVariable("productId") Long productId,
                             @CookieValue(name = "cart_token",required = false) String cartToken,
                             HttpServletResponse response,
                             HttpServletRequest request){

        String userToken= (String) request.getAttribute("user");
        if (userToken!=null){
            //说明已经登录
            cartService.del(userToken,productId);
        }
        //说明用户在未登录状态下使用过购物车
        if (cartToken!=null){
            ResultBean resultBean = cartService.del(cartToken, productId);
            reFlushCookie(cartToken,response);
            return  resultBean;
        }
        //如果用户没有使用过购物车，则返回false
        return new ResultBean("404",false);
    }

    @GetMapping("update/{productId}/{count}")
    public ResultBean update(@PathVariable("productId") Long productId,
                             @PathVariable("count") Integer count,
                             @CookieValue(name="cart_token",required = false) String cartToken,
                             HttpServletRequest request,
                             HttpServletResponse response){
        String userToken= (String) request.getAttribute("name");
        if (userToken!=null){
            //用户登录状态使用过购物车
            return cartService.update(userToken,productId,count);
        }
        //用户未登录状态使用过购物车
        if (cartToken!=null){
            ResultBean resultBean = cartService.update(cartToken, productId, count);
            reFlushCookie(cartToken,response);
            return resultBean;
        }
        //用户没有使用过购物车
        return new ResultBean("404",false);
    }

    private void reFlushCookie(String cartToken, HttpServletResponse response) {
        //将cookie写入客户端
        Cookie cookie=new Cookie("cart_token", cartToken);
        cookie.setPath("/");
//        cookie.setDomain("jiang.com");
        cookie.setMaxAge(10*24*60*60);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }
}
