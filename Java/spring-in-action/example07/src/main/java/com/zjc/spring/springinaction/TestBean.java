package com.zjc.spring.springinaction;


public class TestBean {
    private String context;

    public TestBean(String context) {
        this.context = context;
    }

    public String hello() {
        return "hello " + context;
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "context='" + context + '\'' +
                '}';
    }
}
