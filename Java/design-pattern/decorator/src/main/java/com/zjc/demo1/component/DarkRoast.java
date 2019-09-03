package com.zjc.demo1.component;

public class DarkRoast extends Beverage {

    public DarkRoast() {
        description = "深培咖啡";
    }

    @Override
    public double cost() {
        return 3.99;
    }
}
