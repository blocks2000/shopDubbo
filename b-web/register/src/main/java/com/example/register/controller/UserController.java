package com.example.register.controller;


import api.IUserService;
import bean.TUser;
import com.alibaba.dubbo.config.annotation.Reference;
import com.jiang.pojo.ResultBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("user")
public class UserController {

    @Reference
    private IUserService userService;

    @GetMapping("checkUserNameIsExists/{username}")
    @ResponseBody
    public ResultBean checkUserNameIsExists(@PathVariable("username") String username){
        return  userService.checkUsernameIsExists(username);
    }

    @GetMapping("checkPhoneIsExists/{phone}")
    @ResponseBody
    public ResultBean checkPhoneIsExists(@PathVariable("phone") String phone){
        return userService.checkPhoneIsExists(phone);
    }

    @GetMapping("checkEmailIsExists/{email}")
    @ResponseBody
    public ResultBean checkEmailIsExists(@PathVariable("email") String email){
        return userService.checkEmailIsExists(email);
    }

    //生成验证码
    @PostMapping("generateCode/{identification}")
    @ResponseBody
    public ResultBean generateCode(@PathVariable("generateCode") String identification){
        return userService.generateCode(identification);
    }

    //适合异步请求
    @ResponseBody
    @PostMapping("register")
    public ResultBean register(TUser user){
        return null;
    }

    //适合同步请求，跳转到相关页面
    @PostMapping("register4PC")
    public String register4PC(TUser user){
        return null;
    }

    @GetMapping("activating")
    public String activating(String token){
        return null;
    }
}
