package com.zqf.exer;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author oscarzqf
 * @description
 * @create 2021-09-04-17:56
 */
public class ThreadTest {
    public static void main(String[] args) {
        ExecutorService service= Executors.newFixedThreadPool(5);
        try {
            service.submit(new MyThread()).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        service.shutdown();
    }
}
class MyThread implements Callable {


    public MyThread() {
    }



    @Override
    public Object call() throws Exception {
        return 666;

    }
}