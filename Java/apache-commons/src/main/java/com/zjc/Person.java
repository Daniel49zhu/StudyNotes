package com.zjc;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class Person {
    String name;
    int age;
    Date birth;
    List<String> hobby;
}
