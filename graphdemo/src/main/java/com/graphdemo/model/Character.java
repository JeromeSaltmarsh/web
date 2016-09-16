package com.graphdemo.model;

import java.util.ArrayList;
import java.util.List;

public class Character {

    private static int ID_COUNT = 0;

    private int id;
    private String name;
    private int age;

    private List<Character> friends;

    public Character(String name, int age) {
        this.id = ID_COUNT++;
        this.name = name;
        this.age = age;
        this.friends = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public List<Character> getFriends() {
        return friends;
    }
}
