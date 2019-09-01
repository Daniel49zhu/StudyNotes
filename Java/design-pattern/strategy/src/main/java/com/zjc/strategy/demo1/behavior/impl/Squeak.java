package com.zjc.strategy.demo1.behavior.impl;

import com.zjc.strategy.demo1.behavior.QuackBehavior;

public class Squeak implements QuackBehavior {
    public void quack() {
        //实现鸭子吱吱叫
        System.out.println("吱吱吱吱吱吱吱......");
    }
}