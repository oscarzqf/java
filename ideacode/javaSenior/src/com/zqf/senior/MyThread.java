package com.zqf.senior;
/**
 * @author oscarzqf
 * @description
 * @create 2021-08-10-15:33
 */
public class MyThread extends Thread{
    public void run(){
        for (int i = 0; i <=100; i++) {
            if(i==90) {
                try {
                   sleep(1000);
                }catch(InterruptedException e){

                    e.printStackTrace();
                }

            }
            System.out.println(i);
        }

    }
}
