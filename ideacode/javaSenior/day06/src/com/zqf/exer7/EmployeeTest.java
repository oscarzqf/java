package com.zqf.exer7;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * @author oscarzqf
 * @description
 * @create 2021-08-17-12:18
 */
public class EmployeeTest {
    public static void main(String[] args) {
        Comparator<Employee> com=new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                    MyData d1=o1.getBirthday();
                    MyData d2=o2.getBirthday();
                    int myear=d1.getYear()-d2.getYear();
                    if(myear!=0){
                        return myear;
                    }
                    int mnonth=d1.getMonth()-d2.getMonth();
                    if(mnonth!=0){
                        return mnonth;

                    }
                    int myday=d1.getDay()-d2.getDay();
                    if(myday!=0){
                        return myday;
                    }
                    return 0;
                }
        };
        TreeSet<Employee>set=new TreeSet<>(com);
        Employee e1=new Employee("liudehua",55,new MyData(1998,9,5));
        Employee e2=new Employee("wuyifan",45,new MyData(1968,8,2));
        Employee e3=new Employee("zhangxueyou",35,new MyData(1994,7,1));
        Employee e4=new Employee("chcecnglong",25,new MyData(2000,6,9));
        Employee e5=new Employee("guodegang",42,new MyData(2001,5,22));
        set.add(e1);
        set.add(e2);
        set.add(e3);
        set.add(e4);
        set.add(e5);
        set.add(e5);
        Iterator<Employee> iterator = set.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }


    }
}
