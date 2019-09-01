package com.zjc.strategy.demo1.behavior.impl;

import com.zjc.strategy.demo1.behavior.FlyBehavior;

public class FlyWithWings implements FlyBehavior {
    public void fly() {
        System.out.println("我飞起来了!!!");
    }
}