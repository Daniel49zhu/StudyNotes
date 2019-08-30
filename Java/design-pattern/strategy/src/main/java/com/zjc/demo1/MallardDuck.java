package com.zjc.demo1;

import com.zjc.demo1.behavior.impl.FlyNoWay;
import com.zjc.demo1.behavior.impl.Quack;

public class MallardDuck extends Duck {

    public MallardDuck() {
        flyBehavior = new FlyNoWay();
        quackBehavior = new Quack();
    }

    @Override
    public void display() {
        System.out.println("我可是一只货真价实的野鸭子！");
    }
}