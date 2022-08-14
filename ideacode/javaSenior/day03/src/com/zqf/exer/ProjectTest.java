package com.zqf.exer;

/**
 * @author oscarzqf
 * @description
 * @create 2021-08-12-17:06
 */
public class ProjectTest {
    public static void main(String[] args) {
        Clerk cl=new Clerk();
        Customers customer=new Customers(cl);
        Producer producer=new Producer(cl);
        customer.start();
        producer.start();
    }

}
class Clerk{
    private int productCount=0;
    //两个同步方法使用同一把锁this，所有一个进去，另一个需要等待
    public synchronized void produceProduct(){
        if(productCount<20){
            System.out.println(Thread.currentThread().getName()
                    +"开始生产第"+(productCount+1)+"个产品");
        productCount++;
        notify();//生产后唤醒消费者
        }else{
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public synchronized void consumeProduct(){
        if(productCount>0){
            System.out.println(Thread.currentThread().getName()
            +"开始消费第"+productCount+"个产品");
            productCount--;
            notify();//消费后唤醒生产者
        }else {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

class Producer extends Thread{
    private Clerk clerk;
    public Producer(Clerk clerk){
        this.clerk=clerk;
    }

    @Override
    public void run() {

        while (true) {
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.produceProduct();

        }
    }
}
class Customers extends Thread{
    private Clerk clerk;
    public Customers(Clerk clerk){
        this.clerk=clerk;
    }

    @Override
    public void run() {
        while (true) {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.consumeProduct();
        }
    }
}