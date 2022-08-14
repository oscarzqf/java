package com.zqf.javas;

/**
 * @author oscarzqf
 * @description
 * @create 2021-08-29-10:40
 */
public class Person extends Creature<String>{
    private String name;
    public int id;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + id +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
