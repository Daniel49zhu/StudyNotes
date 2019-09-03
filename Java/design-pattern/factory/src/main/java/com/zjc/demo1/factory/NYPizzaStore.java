package com.zjc.demo1.factory;

import com.zjc.demo1.product.NYStyleCheesePizza;
import com.zjc.demo1.product.NYStyleClamPizza;
import com.zjc.demo1.product.Pizza;

public class NYPizzaStore extends PizzaStore {

    @Override
    Pizza createPizza(String type) {

        if ("cheese".equals(type)) {
            return new NYStyleCheesePizza();
        } else if ("clam".equals(type)) {
            return new NYStyleClamPizza();
        }
        return null;
    }
}