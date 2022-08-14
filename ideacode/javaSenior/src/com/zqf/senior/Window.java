package com.zqf.senior;

/**
 * @author oscarzqf
 * @description  创建三个窗口共卖100张票，但是存在线程安全问题没有解决
 * @create 2021-08-11-12:47
 */
public class Window extends Thread{
    private static int ticket=100;//设置为共用静态
    @Override//创建三个窗口共卖100张票
    public void run() {
        while (ticket!=0){
            show();
        }
    }
    private static synchronized void show(){//同步监视器：Window.class
    //private synchronized void show(){//同步监视器t1,t2,t3
        if(ticket>0) {
            System.out.println(Thread.currentThread().getName() +
                    ":卖票，票号为：" + ticket);
            ticket--;
        }
    }
}
class WindowTest{
    public static void main(String[] args) {
        Window w1=new Window();
        Window w2=new Window();
        Window w3=new Window();
        w1.start();
        w2.start();
        w3.start();
    }
}