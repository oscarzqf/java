package com.zqf.exer2;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author oscarzqf
 * @description
 * @create 2021-08-12-17:48
 */
//1.创建一个实现Callable的实现类
public class ThreadNew implements Callable {
    @Override
    //2.实现call()方法，将此线程所需操作放入
    public Object call() throws Exception {
        int sum=0;
        for (int i = 0; i <=100; i++) {
            if(i%2==0){
                System.out.println(i);
                sum+=i;
            }
        }
        return sum;
    }
}
class ThrewNewTest{
    public static void main(String[] args) {
        //3.创建Callable()接口实现类的对象
        ThreadNew threadnew=new ThreadNew();
        //4.将此Callable()接口实现类的对象作为参数传递到FutureTask
        //构造器中，创建FutureTask对象
        FutureTask futureTask=new FutureTask(threadnew);
        //5.将FutureTask的对象作为参数传递到Thread类的构造器中创建
        //Thread对象，并调用start()
        new Thread(futureTask).start();
        try {
            //6.可以获取call方法的返回值
            //get()返回值即是重写call的返回值
            Object sum=futureTask.get();
            System.out.println(sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}