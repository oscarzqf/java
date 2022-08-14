package com.zqf.exer;

import java.util.Date;

/**
 * @author oscarzqf
 * @description
 * @create 2021-08-12-15:54
 */

public class Account {
    private double balance;
    public Account(double balance){
        this.balance=balance;
    }

    public double getBalance() {
        return balance;
    }

    public synchronized void setBalance(double balance) {
        if(this.balance>=balance){
        this.balance -= balance;
        System.out.println(Thread.currentThread().getName()+"取钱成功，余额为："+this.balance);
        }else{
            System.out.println("余额不足");
        }
    }
}

class Customer extends Thread{
    private Account act;
    public Customer(Account act){
        this.act=act;
    }

    @Override
    public void run() {
        for (int i = 0; i <=3; i++) {
            act.setBalance(1000);
        }
    }
}

class Test{
    public static void main(String[] args) {
        Account act=new Account(5000);
        Customer c1=new Customer(act);
        Customer c2=new Customer(act);
        c1.start();
        c2.start();
        String s1="12334";
        int[] a=new int[]{1,2,3};
        System.out.println(a.length);
        System.out.println(s1.length());

        //JDK8之前日期和时间API
        //1.system类中的currentTimeMillis()
        long t1=System.currentTimeMillis();//获取时间戳
        System.out.println(t1);

        //2.java.util.Date类
        //-----两个构造器的使用-------
        //构造器一Date()创建当前时间的Date对象
        Date date=new Date();
        //构造器二:创建指定时间戳的Date对象
        Date date1=new Date(10000000000L);
        //-----两个方法的使用---------
        System.out.println(date.toString());//显示Fri Aug 13 17:40:06 CST 2021
        System.out.println(date.getTime());//获取当前对象的时间戳

        //3.java.sql.Date（util下Data的子类）对应着数据库中的日期类型的变量
        //实例化
        java.sql.Date date2=new java.sql.Date(111111111);
        System.out.println(date2.toString());//1970-01-02,只显示年月日
        //将java.util.Data对象转换为java.sql.Data
        Date date3=new Date();
        java.sql.Date date4=new java.sql.Date(date3.getTime());

    }
}
/*class test99 {
String str=new String();//char[] value=new char[0];
String str1=new String("abc");//char[] value=char[]{'a','b','c'};

StringBuffer sb1=new StringBuffer();//char [] value=new char[16];底层创建了长度为16的char[]数组
sb1.append('a');//value[0]='a';
StringBuffer sb2=new StringBuffer("abc");//char[] value=new char["abc".length+16];


}*/








