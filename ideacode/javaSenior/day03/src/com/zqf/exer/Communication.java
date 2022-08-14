package com.zqf.exer;

/**
 * @author oscarzqf
 * @description
 * @create 2021-08-12-16:21
 */
public class Communication implements Runnable{
    private int number=1;
    @Override
    public void run() {
        while (true){
            synchronized (this) {
                notifyAll();//唤醒所有等待
                if (number <= 100) {
                    System.out.println(Thread.currentThread().getName() + number);
                    number++;
                    try {
                        //使得调用此方法的线程进入阻塞状态
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    break;
                }
            } } }}
class TestCommunication{
    public static void main(String[] args) {
        Communication c1=new Communication();
        new  Thread(c1).start();
        new Thread(c1).start();
    }
}