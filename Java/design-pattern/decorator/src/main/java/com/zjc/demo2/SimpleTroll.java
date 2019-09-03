package com.zjc.demo2;

public class SimpleTroll implements  Troll {
    public void attack() {
        System.out.println("The troll tries to grab you!");
    }

    public int getAttackPower() {
        return 10;
    }

    public void fleeBattle() {
        System.out.println("The troll shrieks in horror and runs away!");
    }
}
