package com.zjc.services;

import com.zjc.beans.BeanOne;
import com.zjc.beans.BeanTwo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
@Component
public class AutowiredAnnoService {
    @Resource
    private BeanOne beanOne;
    @Autowired
    private BeanTwo beanTwo;
    @Autowired
    private ApplicationContext context;

    public void sayHello() {
        System.out.println("I'm " + beanOne.getName());
        System.out.println("I'm " + beanTwo.getName());
    }

    public ApplicationContext getContext() {
        return context;
    }
}
