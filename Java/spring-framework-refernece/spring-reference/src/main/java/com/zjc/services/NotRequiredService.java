package com.zjc.services;

import org.springframework.beans.factory.annotation.Autowired;

public class NotRequiredService {

    @Autowired(required = false)
    private HelloService helloService;

    public void sayHello() {
        helloService.sayHello();
    }
}
