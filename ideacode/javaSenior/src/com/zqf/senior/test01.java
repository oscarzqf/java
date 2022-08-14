package com.zqf.senior;

public class test01 {
    public static void main(String[] args) {
        MyThread t1=new MyThread();
        //线程执行
        t1.start();
        //main线程中执行
        for (int i=0;i<1000;i++) System.out.print("*");
    }
}
