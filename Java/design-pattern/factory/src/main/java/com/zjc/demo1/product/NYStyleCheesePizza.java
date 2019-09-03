package com.zjc.demo1.product;

public class NYStyleCheesePizza extends Pizza {

    public NYStyleCheesePizza(){
        name = "纽约风情烤肉披萨";
        dough = "中国大东北进口的高级白面";
        sauce = "纽约风味的风情烤肉";
    }

    @Override
    public void box() {
        System.out.println("将披萨装进印有纽约店logo的包装盒里");
    }
}