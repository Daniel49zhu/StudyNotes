package com.zjc.demo1.factory;

import com.zjc.demo1.product.ChicagoStyleCheesePizza;
import com.zjc.demo1.product.ChicagoStyleClamPizza;
import com.zjc.demo1.product.Pizza;

public class ChicagoPizzaStore extends PizzaStore {
    Pizza createPizza(String type) {
        if ("cheese".equals(type)) {
            return new ChicagoStyleCheesePizza();
        } else if ("clam".equals(type)) {
            return new ChicagoStyleClamPizza();
        }
        return null;
    }
}
