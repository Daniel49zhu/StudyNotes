package com.zjc.strategy.demo1.behavior.impl;

import com.zjc.strategy.demo1.behavior.QuackBehavior;

public class MuteQuack implements QuackBehavior {
    public void quack() {
        System.out.print("整个世界都清净了....  ps：我不会叫 :(");
    }
}