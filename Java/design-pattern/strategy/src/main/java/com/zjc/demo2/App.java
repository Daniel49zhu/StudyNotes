package com.zjc.demo2;

public class App {
    public static void main(String[] args) {
        // GoF Strategy pattern
        DragonSlayer dragonSlayer = new DragonSlayer(new MeleeStrategy());
        dragonSlayer.goToBattle();
        dragonSlayer.changeStrategy(new ProjectileStrategy());
        dragonSlayer.goToBattle();
        dragonSlayer.changeStrategy(new SpellStrategy());
        dragonSlayer.goToBattle();

        // Java 8 Strategy pattern
        dragonSlayer = new DragonSlayer(() -> System.out.println("Hello World!"));
        dragonSlayer.goToBattle();
        dragonSlayer.changeStrategy(() -> System.out.println("Hello World!!"));
        dragonSlayer.goToBattle();
        dragonSlayer.changeStrategy(() -> System.out.println("Hello World!!!"));
        dragonSlayer.goToBattle();
    }
}
