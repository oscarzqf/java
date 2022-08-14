package com.zqf.dome;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author oscarzqf
 * @description
 * @create 2021-08-11-13:07
 */
public class MyyThread implements Runnable {
    private int ticket = 100;
    private ReentrantLock lock=new ReentrantLock();
    public void run() {
           while(true){
               try {
                   lock.lock();//锁上方法
                   if (ticket > 0) {
                       System.out.println(Thread.currentThread().getName()
                               + ":卖票，票号为：" + ticket);
                       ticket--;
                   } else {
                       break;
                   }
               }finally {
                   lock.unlock();//解锁方法
               }
           }
    }
}
class MyyThreadTest{
    public static void main(String[] args) {
        MyyThread m1=new MyyThread();//一个对象
        Thread t1=new Thread(m1);
        t1.setName("ticket000 1");
        t1.start();
        //再启动一个线程
        Thread t2=new Thread(m1);
        t2.setName("ticket000 2");
        t2.start();
    }
}
