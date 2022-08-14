package com.zqf.exer7;

/**
 * @author oscarzqf
 * @description
 * @create 2021-08-17-12:14
 */
public class Employee {
    private String name;
    private int age;
    private MyData birthday;

    public Employee(String name, int age, MyData birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    public Employee() {
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public MyData getBirthday() {
        return birthday;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setBirthday(MyData birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                '}';
    }

    /*@Override
    public int compareTo(Object o) {
        if(o instanceof Employee){
            Employee e=(Employee)o;
            return this.name.compareTo(e.name);
        }
        throw new RuntimeException("传入的数据不一致！");
    }*/
}
