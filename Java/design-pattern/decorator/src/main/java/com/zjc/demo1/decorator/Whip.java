package com.zjc.demo1.decorator;

import com.zjc.demo1.component.Beverage;

public class Whip extends CondimentDecorator {

    private Beverage beverage;

    public Whip(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return this.beverage.getDescription() + "，加奶泡";
    }

    @Override
    public double cost() {
        return this.beverage.cost() + .88;
    }
}
