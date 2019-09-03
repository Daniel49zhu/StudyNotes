package com.zjc.demo1.decorator;

import com.zjc.demo1.component.Beverage;

public class Mocha extends CondimentDecorator {

    private Beverage beverage;

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return this.beverage.getDescription() + "，加摩卡";
    }

    @Override
    public double cost() {
        return this.beverage.cost() + 1.99;
    }
}
