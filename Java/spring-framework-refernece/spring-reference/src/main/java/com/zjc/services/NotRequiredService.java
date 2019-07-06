package com.zjc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class NotRequiredService {
    //注入一个未在Spring容器中声明的Bean，为了防止报错增加required属性
//    @Autowired(required = false)
//    private HelloService helloService;

//    @Autowired
//    private Optional<HelloService> helloService;

    @Autowired
    @Nullable
    private  HelloService helloService;

    public void sayHello() {
        helloService.sayHello();
    }
}
