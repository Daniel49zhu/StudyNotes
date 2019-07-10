package com.zjc.services;

import com.zjc.beans.BeanOne;
import com.zjc.beans.BeanTwo;
import org.aspectj.lang.annotation.Pointcut;

public class HelloService {

    private BeanOne beanOne;

    private BeanTwo beanTwo;

    public HelloService() {
    }

    public HelloService(BeanTwo beanTwo) {
        this.beanTwo = beanTwo;
    }

    public void setBeanOne(BeanOne beanOne) {
        this.beanOne = beanOne;
    }

    public void sayHello() {
        System.out.println("Hello World!");
    }

    public BeanOne getBeanOne() {
        return beanOne;
    }

    public BeanTwo getBeanTwo() {
        return beanTwo;
    }
}
