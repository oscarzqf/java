package com.zqf.exer;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author oscarzqf
 * @description
 * @create 2021-08-30-19:20
 */
public class LambdaTest {
    public static void main(String[] args) {
        //对象：：实例方法
        //Consumer中的void accept(T t)
        //PrintStream中的void printLn(T t)
        PrintStream ps=System.out;
        Consumer<String> cust=ps::println;
        cust.accept("测试方法引用的例子");

        //类：：静态方法
        //Comparator中的int compare(T t1,T t2)
        //Integer中的int compare(T t1,T t2)
        Comparator<Integer> com1=Integer::compare;
        System.out.println(com1.compare(12,3));

        //类：：非静态方法（难）
        //Comparator中的 int compare(T t1,T t2)
        //String 中的int t1.compareTo(t2)
        Comparator<String> com2=String::compareTo;
        System.out.println(com2.compare("abc","bcd"));

        //构造器引用
        //Supplier中的T get()
        //Employee类的空参构造器：Employeed()
        Supplier<Employee> sup= Employee::new;
        //Function中的R  apply(T t)
        Function<String, Employee> fn= Employee::new;
        //BiFunction中的R apply(T t,U u)

        //可以把数组看做一个特殊的类，则写法与构造器一致
        //Function中的R  apply(T t)
        Function<Integer,String[]>fun=String[]::new;
        String[] apply = fun.apply(10);
        for(String val:apply){
            System.out.println(val);
        }
    }
}
class Employee{
    private String name;
    private double salary;

    public double getSalary() {
        return salary;
    }

    public Employee() {
    }

    public Employee(String name) {
        this.name = name;
    }
}