package com.zqf.exer;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Objects;

/**
 * @author oscarzqf
 * @description
 * @create 2021-09-01-9:25
 */
public class JavaNewFieldsTest {
    public static void main(String[] args) {
        HashSet hashSet=new HashSet();
        Person p1=new Person(1001,"AA");
        Person p2=new Person(1002,"BB");
        hashSet.add(p1);
        hashSet.add(p2);
        System.out.println(hashSet);
        p1.setName("CC");
        hashSet.remove(p1);
        System.out.println(hashSet);


    }
}
class Person{
    private int id;
    private String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && Objects.equals(name, person.name);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}