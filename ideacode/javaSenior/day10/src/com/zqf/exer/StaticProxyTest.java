package com.zqf.exer;

import java.sql.SQLOutput;

/**
 * 静态代理举例
 * 特点：代理类和被代理类在编译期间就已经确定下来了
 * @author oscarzqf
 * @description
 * @create 2021-08-30-13:01
 */
interface ClothFactory{

    void produceCloth();
}
//代理类
class ProxyClothFactory implements ClothFactory{
    private ClothFactory factory;//用被代理对象进行实例化

    public ProxyClothFactory(ClothFactory factory) {
        this.factory = factory;
    }

    @Override
    public void produceCloth() {
        System.out.println("准备工作");
        factory.produceCloth();
        System.out.println("结束工作");
    }
}
//被代理类
class NikeClothFactory implements ClothFactory{
    @Override
    public void produceCloth() {
        System.out.println("工厂生产一批Nike衣服");
    }
}
public class StaticProxyTest {
    public static void main(String[] args) {
        ClothFactory nike=new NikeClothFactory();
        ClothFactory factory=new ProxyClothFactory(nike);
        factory.produceCloth();
    }
}
