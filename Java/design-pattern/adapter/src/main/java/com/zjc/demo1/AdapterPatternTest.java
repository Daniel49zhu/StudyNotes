package com.zjc.demo1;

import com.zjc.demo1.adaptee.WildTurkey;
import com.zjc.demo1.adapter.TurkeyAdapter;
import com.zjc.demo1.target.Duck;
import com.zjc.demo1.target.MallardDuck;

public class AdapterPatternTest {

    public static void main(String[] args) {

        MallardDuck duck = new MallardDuck();
        WildTurkey turkey = new WildTurkey();

        TurkeyAdapter adapter = new TurkeyAdapter(turkey);

        System.out.println("==========火鸡===========");
        turkey.gobble();
        turkey.fly();

        System.out.println("\n==========鸭子===========");
        testDuck(duck);

        System.out.println("\n==========适配器===========");
        testDuck(adapter);
    }

    static void testDuck(Duck duck) {
        duck.quack();
        duck.fly();
    }
}