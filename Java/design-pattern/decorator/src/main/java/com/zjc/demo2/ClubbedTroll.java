package com.zjc.demo2;

public class ClubbedTroll implements Troll {

    private Troll decorated;

    public ClubbedTroll(Troll decorated) {
        this.decorated = decorated;
    }

    public void attack() {
        decorated.attack();
        System.out.println("The troll swings at you with a club!");
    }

    public int getAttackPower() {
        return decorated.getAttackPower() + 10;
    }

    public void fleeBattle() {
        decorated.fleeBattle();
    }
}
