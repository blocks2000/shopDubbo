package com.jiang.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

//封装状态信息码
@Data
@AllArgsConstructor
public class ResultBean<T> implements Serializable{
    //返回的状态码
    private String statusCode;
    //如果成功，返回的数据
    private  T data;

}
