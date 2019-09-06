package com.zjc.demo1;

public class StatePatternTest {
    public static void main(String[] args) {
        GumballMachine machine = new GumballMachine(10);
        machine.insertQuarter();
        machine.turnCrank();
        System.out.println("剩余糖果："+machine.getCount()+"\n");

        machine.ejectQuarter();
        machine.turnCrank();
        System.out.println("剩余糖果：" + machine.getCount() + "\n");

        machine.insertQuarter();
        machine.ejectQuarter();
        System.out.println("剩余糖果：" + machine.getCount() + "\n");

        machine.insertQuarter();
        machine.turnCrank();
        System.out.println("剩余糖果："+machine.getCount()+"\n");

    }
}
