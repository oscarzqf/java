package com.zqf.exer3;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author oscarzqf
 * @description
 * @create 2021-08-12-18:32
 */
public class ExecutorTest {
    public static void main(String[] args) {
        //1.提供指定线程数量的线程池,这里有多态性
        ExecutorService service=Executors.newFixedThreadPool(10);
        //如果想设置属性需要向下转型为接口的具体实现类

        //2.执行指定线程的操作
        //service.execute(new IntPut());//适用于Runnable
        //service.submit(new IntPut());//适用于Callable
        //3.关闭线程池
        service.shutdown();
    }
}
class IntPut implements Callable {//Callable/Runnable
    @Override
    public Object call() throws Exception {
        for(int i=0;i<101;++i){
            System.out.println(i);
        }
        return null;
    }
}