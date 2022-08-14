package com.zqf.senior;

/**
 * @author oscarzqf
 * @description  单例懒汉式实现完善
 * @create 2021-08-11-17:10
 */
public class Bank {
    private Bank(){}
    private static Bank instance=null;
    public static Bank getInstance(){
        if(instance==null) {
            synchronized (Bank.class) {
                if(instance==null){
                    instance = new Bank();
                }
            }
        }
        return instance;
    }
}
