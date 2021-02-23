package com.example.userservice.service;

import api.IUserService;
import bean.TUser;
import com.alibaba.dubbo.config.annotation.Service;
import com.jiang.base.BaseServiceImpl;
import com.jiang.base.IBaseDao;
import com.jiang.pojo.ResultBean;
import com.jiang.util.CodeUtils;
import mapper.TUserMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


@Service
@Component
public class userServiceImpl extends BaseServiceImpl<TUser> implements IUserService {
    @Autowired
    private TUserMapper userMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Resource(name = "myStringRedisTemplate")
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public ResultBean checkUsernameIsExists(String username) {
        TUser user = userMapper.selectByUserName(username);
        if (user==null){
            return new ResultBean("500","未找到此用户");
        }
        return new ResultBean("200",user);
    }

    @Override
    public ResultBean checkEmailIsExists(String email) {
        TUser user = userMapper.selectByEmail(email);
        if (user==null){
            return new ResultBean("500","未找到此用户");
        }
        return new ResultBean("200",user);
    }

    @Override
    public ResultBean checkPhoneIsExists(String phone) {
        TUser user = userMapper.selectByPhone(phone);
        if (user==null){
            return new ResultBean("500","未找到此用户");
        }
        return new ResultBean("200",user);
    }

    @Override
    public ResultBean generateCode(String identification) {
        //生成验证码
        String code= CodeUtils.generateCode(6);
        //往redis保存一个凭证和验证码的对应关系
        redisTemplate.opsForValue().set(identification,code,2, TimeUnit.MINUTES);
        //给用户的手机号码发送消息
        //发送消息给消息队列，异步处理发送短信
        //可以把凭证和验证码放入一个map中，然后发送到消息队列
        Map<String,String> params=new HashMap<>();
        params.put("identification",identification);
        params.put("code",code);
        rabbitTemplate.convertAndSend("sms-exchange","sms.code",params);
        return new ResultBean("200","ok");
    }

    @Override
    public ResultBean checkLogin(TUser user) {
        //根据用户输入的用户名查询查询数据库中的内容
        TUser currentUser=userMapper.selectByIdentification(user.getUsername());
        //如果有这个用户，则进行密码对比并取出用户名，并传回200
        if(currentUser!=null){
            if (currentUser.getPassword().equals(user.getPassword())){
                //保存一个用户凭证到redis里面，相当于jsessionID
                //key是自己设置的，为user:token:uuid
                String uuid = UUID.randomUUID().toString();
                //将凭证写入redis
                redisTemplate.opsForValue().set("user:token:"+uuid,currentUser.getUsername(),30,TimeUnit.MINUTES);
                //将uuid返回给客户端
                Map<String,String> params=new HashMap<>();
                params.put("uuid",uuid);
                params.put("username",currentUser.getUsername());
                return new ResultBean("200",params);
            }
        }
        return new ResultBean("404",null);
    }

    @Override
    public ResultBean checkIsLogin(String uuid) {
        //拼接存在redis中的key
        StringBuilder key=new StringBuilder("user:token:").append(uuid);
        //根据拼接好的key,去redis中查找是否有此凭证
        String username= (String) redisTemplate.opsForValue().get(key.toString());
        //如果找出用户名，则说明此用户已经登陆
        if (username!=null){
            //如果用户还有操作，则刷新凭证的有效期
            redisTemplate.expire(key.toString(),30,TimeUnit.MINUTES);
            return new ResultBean("200",username);
        }
        return new ResultBean("404",null);
    }

    @Override
    public IBaseDao<TUser> getBaseDao() {
        return userMapper;
    }
}
