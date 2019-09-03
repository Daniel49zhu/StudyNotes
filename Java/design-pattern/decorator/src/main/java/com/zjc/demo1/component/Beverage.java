package com.zjc.demo1.component;

public abstract class Beverage {
    String description = "还不知道是什么饮料";

    /**
     * @return 返回饮料详细描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 计算价格
     *
     * @return double类型的价格
     */
    public abstract double cost();
}
