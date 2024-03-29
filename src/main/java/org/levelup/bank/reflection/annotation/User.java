package org.levelup.bank.reflection.annotation;

import lombok.ToString;

@ToString
public class User {
    private String name;
    @RandomString(length = 30,isUpperCase = true)
    private String lastName;
    @RandomInt(min = 0,max = 100)
    private int age;
    @RandomInt(min=0)
    private int identifier;
}
