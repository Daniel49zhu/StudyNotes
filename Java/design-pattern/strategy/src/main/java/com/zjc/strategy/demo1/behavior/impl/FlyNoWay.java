package com.zjc.strategy.demo1.behavior.impl;

import com.zjc.strategy.demo1.behavior.FlyBehavior;

public class FlyNoWay implements FlyBehavior {
    public void fly() {
        System.out.println(" I can't fly");
    }
}
