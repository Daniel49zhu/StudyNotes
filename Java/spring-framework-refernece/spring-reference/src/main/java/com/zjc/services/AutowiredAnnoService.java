package com.zjc.services;

import com.zjc.beans.BeanOne;
import com.zjc.beans.BeanTwo;
import org.springframework.beans.factory.annotation.Autowired;

public class AutowiredAnnoService {
    @Autowired
    private BeanOne beanOne;
    @Autowired
    private BeanTwo beanTwo;

    public void sayHello() {
        System.out.println("I'm " + beanOne.getName());
        System.out.println("I'm " + beanTwo.getName());
    }
}
