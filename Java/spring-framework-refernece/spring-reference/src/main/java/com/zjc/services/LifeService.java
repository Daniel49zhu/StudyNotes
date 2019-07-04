package com.zjc.services;

import org.springframework.context.SmartLifecycle;

public class LifeService implements SmartLifecycle {
    public void start() {
        System.out.println("I'm start");
    }

    public void stop() {
        System.out.println("I'm stopped");
    }

    public boolean isRunning() {
        System.out.println("I'm Running?");
        return false;
    }

    public void sayHello() {
        System.out.println("I'm ListService");
    }
}
