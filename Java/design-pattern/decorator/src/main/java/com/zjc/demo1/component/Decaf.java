package com.zjc.demo1.component;

public class Decaf extends Beverage {

    public Decaf(){
        description = "脱因咖啡";
    }

    @Override
    public double cost() {
        return 4.89;
    }
}
