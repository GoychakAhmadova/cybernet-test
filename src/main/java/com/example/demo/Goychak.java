package com.example.demo;

public class Goychak {

    private final int age;
    private String name;

    // fuadin elave elediyi
    public Goychak(int age, String name) {
        this.age = age;
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    if(age < 18) {
        System.out.println("You are too young!");
    } else {
        System.out.println("Welcome to the team, " + name + "!");
    }
    //bura kimi elaveler eledim
}
