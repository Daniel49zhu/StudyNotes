package com.zjc.services;

import com.zjc.beans.BeanOne;
import com.zjc.beans.BeanTwo;

public class AutowiredService {
    private BeanOne beanOne;
    private BeanTwo beanTwo;

    public void setBeanOne(BeanOne beanOne) {
        this.beanOne = beanOne;
    }

    public void setBeanTwo(BeanTwo beanTwo) {
        this.beanTwo = beanTwo;
    }

    public BeanOne getBeanOne() {
        return beanOne;
    }

    public BeanTwo getBeanTwo() {
        return beanTwo;
    }
}
