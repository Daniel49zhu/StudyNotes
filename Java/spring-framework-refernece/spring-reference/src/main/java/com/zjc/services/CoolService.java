package com.zjc.services;

import javax.annotation.PostConstruct;

public class CoolService {
    private int a;
    private int b;
    private int v;

    public CoolService(int a, int b, int v) {
        this.a = a;
        this.b = b;
        this.v = v;
    }

    @Override
    public String toString() {
        return "CoolService{" +
                "a=" + a +
                ", b=" + b +
                ", v=" + v +
                '}';
    }

    @PostConstruct
    public void intiMd() {
        System.out.println("Bean has been initialized!!!");
    }
}
