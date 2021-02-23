package com.example.cartservice.service;

import api.ICartService;
import com.alibaba.dubbo.config.annotation.Service;
import com.jiang.pojo.ResultBean;
import org.springframework.data.redis.core.RedisTemplate;
import pojo.CartItem;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class CartServiceImpl implements ICartService {

    @Resource(name = "myStringRedisTemplate")
    private RedisTemplate redisTemplate;

    @Override
    public ResultBean add(String token, Long productId, Integer count) {
        //创建跟key对应的字符串，方便后续查询
        StringBuilder key = new StringBuilder("user:cart:").append(token);
        //根据此key去redis中查询是否有相关记录
        Map cart = redisTemplate.opsForHash().entries(key.toString());
        //如果当前已经有购物车，且购物车里面已经有相同商品，则需要进行修改
        if (cart.size()!=0) {
            if (redisTemplate.opsForHash().hasKey(key.toString(), productId)) {
                //如果已经存在，则需要更改数据
                CartItem cartItem = (CartItem) redisTemplate.opsForHash().get(key.toString(), productId);
                //更改数量
                cartItem.setCount(cartItem.getCount() + count);
                //更新操作时间
                cartItem.setUpdateTime(new Date());
                //保存变更
                redisTemplate.opsForHash().put(key.toString(), productId, cartItem);
                return new ResultBean("200", true);
            }
        }
        //其他情况，比如第一次操作购物车和有购物车但是没有相同商品，则直接在redis中添加
        CartItem cartItem = new CartItem(productId, count, new Date());
//        cartItem.setProductId(productId);
//        cartItem.setUpdateTime(new Date());
//        cartItem.setCount(count);
        //将此购物项加入redis中
        redisTemplate.opsForHash().put(key.toString(), productId, cartItem);
        //设置有效期
        redisTemplate.expire(key.toString(), 15, TimeUnit.DAYS);
        return new ResultBean("200", true);
    }

    @Override
    public ResultBean del(String token, Long productId) {
        StringBuilder key=new StringBuilder("user:cart:").append(token);
        Long delete = redisTemplate.opsForHash().delete(key.toString(), productId);
        if (delete==1){
            return new ResultBean("200",true);
        }
        return new ResultBean("404",false);
    }

    @Override
    public ResultBean update(String token, Long productId, Integer count) {
        StringBuilder key=new StringBuilder("user:cart:").append(token);
        CartItem cartItem = (CartItem) redisTemplate.opsForHash().get(key.toString(), productId);
        //如果有这个商品的购物记录，才进行更新
        if (cartItem!=null){
            redisTemplate.opsForHash().put(key.toString(),productId,new CartItem(productId,count,new Date()));
            return new ResultBean("200",true);
        }
        return new ResultBean("404",false);
    }

    @Override
    public ResultBean query(String token) {
        StringBuilder key=new StringBuilder("user:cart:").append(token);
        Map carts = redisTemplate.opsForHash().entries(key.toString());
        //如果查询到了有这个用户的购物车记录，则取出productid----cartitem
        if (carts.size()>0){
            //此时获取到了values，但是并没有进行排序
            Collection values = carts.values();
            //将此集合中的每一个元素放入treeSet,按照时间进行排序
            TreeSet<CartItem> treeSet=new TreeSet<>();
            for (Object value : values) {
                CartItem cartItem=(CartItem) value;
                treeSet.add(cartItem);
            }
            List<CartItem> result=new ArrayList<>(treeSet);
            return new ResultBean("200",result);
        }
        //如果此用户对应的购物车为空，则返回null
        return new ResultBean("404",null);
    }

    @Override
    public ResultBean merge(String nologinKey, String loginKey) {
       //将未登录购物车合并到已登录购物车
        //判断未登录购物车是否存在
        Map nologinCart = redisTemplate.opsForHash().entries(nologinKey);
        if (nologinCart.size()==0){
            //未登录购物车不存在，则无需合并
            return new ResultBean("200","无需合并");
        }
        //未登录购物车不为空，此时判断已登录购物车是否为空
        Map loginCart = redisTemplate.opsForHash().entries(loginKey);
        if (loginCart.size()==0){
            //已登录购物车为空，则直接将未登录购物车合并到已登录购物车
            redisTemplate.opsForHash().putAll(loginKey,nologinCart);
            //删除未登录购物车
            redisTemplate.delete(nologinKey);
            return new ResultBean("200","合并成功");
        }
        //已登录和未登录购物车都存在
        //1：相同的商品，则进行数量叠加，否则直接把未登录购物车加入到已登录购物车里面
        //以已登录购物车为基准，遍历未登录购物车，不断进行比较
        Set<Map.Entry<Object,Object>> nologinEntrys = nologinCart.entrySet();
        for (Map.Entry<Object, Object> nologinEntry : nologinEntrys) {
            //nologinEntry.getKey()--->productId
            //nologinEntry.getValue--->cartItem
            CartItem cartItem = (CartItem) redisTemplate.opsForHash().get(loginKey, nologinEntry.getKey());
            if (cartItem!=null){
                //已登录购物车里面也有这件商品，则进行叠加
                CartItem nologinCartItem = (CartItem) nologinEntry.getValue();
                cartItem.setCount(cartItem.getCount()+nologinCartItem.getCount());
                cartItem.setUpdateTime(new Date());
            }else {
                //如果已登录购物车没有这件商品，则直接进行添加
                redisTemplate.opsForHash().put(loginKey,nologinEntry.getKey(),nologinEntry.getValue());
            }
        }
        //删除未登录购物车
        redisTemplate.delete(nologinKey);
        return new ResultBean("200","合并成功");
    }
}
