package com.zjc.demo2;

public class App {

  /**
   * Program entry point
   * 
   * @param args command line args
   */
  public static void main(String[] args) {

    // simple troll
    System.out.println("A simple looking troll approaches.");
    Troll troll = new SimpleTroll();
    troll.attack();
    troll.fleeBattle();
    System.out.printf("Simple troll power %s.\n", troll.getAttackPower());
    System.out.println("------------------------------------------");
    // change the behavior of the simple troll by adding a decorator
    System.out.println("A troll with huge club surprises you.");
    Troll clubbedTroll = new ClubbedTroll(troll);
    clubbedTroll.attack();
    clubbedTroll.fleeBattle();
    System.out.printf("Clubbed troll power %s.\n", clubbedTroll.getAttackPower());
    System.out.println("-------------------------------------------");
    System.out.println("A clubbedTroll2 with huge club surprises you.");
    Troll clubbedTroll2 = new ClubbedTroll(clubbedTroll);
    clubbedTroll2.attack();
    clubbedTroll2.fleeBattle();
    System.out.printf("Clubbed troll power %s.\n", clubbedTroll2.getAttackPower());
  }
}