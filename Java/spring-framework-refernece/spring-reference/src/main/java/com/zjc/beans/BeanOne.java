package com.zjc.beans;

import org.springframework.stereotype.Component;

@Component
public class BeanOne {
    private String name = "Bean One";

    public String getName() {
        return name;
    }
}
