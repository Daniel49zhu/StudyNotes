package com.zjc.demo1;

import com.zjc.demo1.behavior.impl.FlyWithWings;
import com.zjc.demo1.behavior.impl.MuteQuack;

public class StrategyPatternTest {

    public static void main(String[] args) {

        Duck duck = new MallardDuck();
        duck.swim();
        duck.display();
        duck.performFly();
        duck.performQuack();

        //现在会飞了
        duck.setFlyBehavior(new FlyWithWings());
        duck.performFly();
        //可是不会叫了
        duck.setQuackBehavior(new MuteQuack());
        duck.performQuack();
    }

}