package com.zjc.strategy.demo1.behavior.impl;

import com.zjc.strategy.demo1.behavior.QuackBehavior;

public class Quack implements QuackBehavior {
    public void quack() {
        System.out.println("嘎嘎嘎嘎嘎嘎嘎......");
    }
}