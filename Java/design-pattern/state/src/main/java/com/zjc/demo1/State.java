package com.zjc.demo1;

public interface State {
    /**
     * 投入25分硬币
     */
    void insertQuarter();

    /**
     * 吐出25分硬币
     */
    void ejectQuarter();

    /**
     * 转动曲柄
     */
    void turnCrank();

    /**
     * 发放糖果
     */
    void dispense();
}
