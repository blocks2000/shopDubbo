package com.example.sso.controller;


import api.IUserService;
import bean.TUser;
import com.alibaba.dubbo.config.annotation.Reference;
import com.jiang.pojo.ResultBean;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("sso")
public class SSOController {

    @Reference
    private IUserService userService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //异步方式检查登录用户
    @PostMapping("checkLogin")
    @ResponseBody
    public ResultBean checkLogin(TUser user,HttpServletResponse response){
        ResultBean resultBean = userService.checkLogin(user);
        //2.如果正确，则在服务端保存凭证信息
        if("200".equals(resultBean.getStatusCode())){
            //TODO 写cookie给客户端，保存凭证
            //1.获取uuid
            String uuid = (String) resultBean.getData();
            //2.创建cookie对象
            Cookie cookie = new Cookie("user_token",uuid);
            cookie.setPath("/");
//            //设置cookie的域名为父域名，这样所有子域名系统都可以访问该cookie，解决cookie的跨域问题
//            cookie.setDomain("jiang.com");
            cookie.setHttpOnly(true);
            //3.写cookie到客户端
            response.addCookie(cookie);
        }
        return resultBean;
    }

    //同步方式检查登录用户
    @PostMapping("checkLogin4PC")
    public String checkLogin4PC(TUser user, HttpServletRequest request, HttpServletResponse response,
                                @CookieValue(name = "cart_token",required = false) String cartToken){
        //判断当前用户信息是否正确
        ResultBean resultBean=userService.checkLogin(user);
        if("200".equals(resultBean.getStatusCode())){
            //写cookie给客户端，保存凭证
            //先获取服务器传过来的uuid
            Map<String,String> datas = (Map<String, String>) resultBean.getData();
            String uuid=datas.get("uuid");
            //创建cookie对象
            Cookie cookie=new Cookie("user_token",uuid);
            cookie.setPath("/");
//            cookie.setDomain("jiang.com");
            cookie.setHttpOnly(true);
            //把cookie写给客户端
            response.addCookie(cookie);
            //发送消息到交换机，user_token和cart_token都需要发送到交换机
            Map<String,String> params=new HashMap<>();
            params.put("nologinKey",cartToken);
            params.put("loginKey",datas.get("username"));
            rabbitTemplate.convertAndSend("sso-exchange","user.login",params);
            //如果正确则跳转到商城首页
            return "redirect:http://localhost:9091/index/show";
        }
        //否则重新跳回登录界面
        return "index";
    }

    @GetMapping("logout")
    @ResponseBody
    public ResultBean logout(@CookieValue(name = "user_token",required = false) String token,
                             HttpServletResponse response){
        //request.getSession().removeAttribute("user");
        if(token != null){
            //2.创建cookie对象
            Cookie cookie = new Cookie("user_token",token);
            cookie.setPath("/");
            cookie.setHttpOnly(true);
//            cookie.setDomain("jiang.com");
            //将cookie清除，设置有效期为0
            cookie.setMaxAge(0);
            //3.写cookie到客户端
            response.addCookie(cookie);
        }
        return new ResultBean("200",true);
    }

    @GetMapping ("logout4PC")
    public String logout4PC(){
        return null;
    }

    //判断用户的登陆状态是否合法
    @GetMapping("checkIsLogin")
    @CrossOrigin//(origins = "*",allowCredentials = "true")  //时ajax能够跨域请求，请求头不同，没有cookie
    @ResponseBody
    public ResultBean checkIsLogin(HttpServletRequest request){
        //获取cookies，然后获取user_token的值
        Cookie[] cookies = request.getCookies();
        if(cookies!=null&&cookies.length>0){
            //遍历所有cookie,找到我们的user_token
            for (Cookie cookie : cookies) {
                if("user_token".equals(cookie.getName())){
                    //如果找到，则取出uuid
                    String uuid=cookie.getValue();
                    //根据此uuid去redis数据库中查询是否有此凭证
                    ResultBean resultBean=userService.checkIsLogin(uuid);
                    return resultBean;
                }
            }
        }
        return new ResultBean("404",null);
    }
}
