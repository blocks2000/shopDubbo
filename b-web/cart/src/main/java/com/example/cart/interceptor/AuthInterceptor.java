package com.example.cart.interceptor;


import api.IUserService;
import com.alibaba.dubbo.config.annotation.Reference;
import com.jiang.pojo.ResultBean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Reference
    private IUserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //拿到cookie值
        Cookie[] cookies = request.getCookies();
        if (cookies!=null) {
            for (Cookie cookie : cookies) {
                //如果找到有这个开头的，则拿出他的value值，进行是否登录判断
                if ("user_token".equals(cookie.getName())){
                    String token=cookie.getValue();
                    ResultBean resultBean = userService.checkIsLogin(token);
                    if("200".equals(resultBean.getStatusCode())){
                        //如果是已经登录的状态，则需要在request域中记录下来此人的标识
                        request.setAttribute("user",resultBean.getData());
                        return true;
                    }
                }
            }
        }
        return true;
    }
}
