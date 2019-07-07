package com.zjc.beans;

import org.springframework.stereotype.Component;

@Component
public class BeanTwo {
    private String name = "Bean Two";

    public String getName() {
        return name;
    }
}
