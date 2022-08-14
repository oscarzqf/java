package com.zqf.dome;

/**
 * @author oscarzqf
 * @description 线程创建的第二个方法
 * @create 2021-08-10-16:04
 */
public class ThreadDemo {
    public static void main(String[] args) {
        (new Thread(){
            public void run(){
                for (int i = 0; i < 101; i++) {
                    System.out.println(i);
                }
            }
        }).start();
    }
}
