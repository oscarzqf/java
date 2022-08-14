package com.zqf.javas;

import java.io.Serializable;

/**
 * @author oscarzqf
 * @description
 * @create 2021-08-29-11:18
 */
public class Creature<T>implements Serializable {
    public String food;
    public String habit;

    public void eat(){
        System.out.println(food);
    }

    @Override
    public String toString() {
        return "Creature{" +
                "food='" + food + '\'' +
                ", habit='" + habit + '\'' +
                '}';
    }
}
