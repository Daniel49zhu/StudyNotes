package com.zjc.demo1;

import com.zjc.demo1.factory.ChicagoPizzaStore;
import com.zjc.demo1.factory.NYPizzaStore;
import com.zjc.demo1.product.Pizza;

public class FactoryPatternTest {

    public static void main(String[] args) {
        NYPizzaStore nyPizzaStore = new NYPizzaStore();
        Pizza pizza = nyPizzaStore.orderPizza("cheese");
        System.out.println("二狗子定了一个" + pizza.getName() + "\n");

        ChicagoPizzaStore chicagoPizzaStore = new ChicagoPizzaStore();
        pizza = chicagoPizzaStore.orderPizza("clam");
        System.out.println("隔壁老王定了一个" + pizza.getName() + "\n");
    }
}