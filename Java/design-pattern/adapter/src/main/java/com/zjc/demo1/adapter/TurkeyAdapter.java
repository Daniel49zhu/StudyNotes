package com.zjc.demo1.adapter;

import com.zjc.demo1.adaptee.Turkey;
import com.zjc.demo1.target.Duck;

public class TurkeyAdapter implements Duck {

    private Turkey turkey;

    public TurkeyAdapter(Turkey turkey) {
        this.turkey = turkey;
    }

    @Override
    public void quack() {
        turkey.gobble();
    }

    @Override
    public void fly() {
        //虽然火鸡也能飞，但是飞行距离太短，需要多次挥动它那萌萌哒的翅膀才能和火鸡飞的一样远
        for (int i = 0; i < 5; i++) {
            turkey.fly();
        }
    }
}
