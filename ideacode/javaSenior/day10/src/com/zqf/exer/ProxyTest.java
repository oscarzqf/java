package com.zqf.exer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理的举例
 * @author oscarzqf
 * @description
 * @create 2021-08-30-15:23
 */
interface Human{
    String getBelief();
    void eat(String food);
}
//被代理类
class SuperMan implements Human{
    @Override
    public String getBelief() {
        return "I believe I can fly!";
    }

    @Override
    public void eat(String food) {
        System.out.println("我喜欢吃"+food);
    }
}
//实现动态代理，需要解决的问题？
//一、如何根据加载到内存中的被代理类，动态的创建一个类及其对象
//二、通过代理类的对象调用方法时，如何动态调用被代理类中同名方法
class ProxyFactory{
    //解决问题1
    public static Object getProxyInstance(Object obj){//obj:被代理类的对象
        MyInvocationHandler handler = new MyInvocationHandler();
        handler.bind(obj);
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(),handler);

    }
}
class MyInvocationHandler implements InvocationHandler{
    private Object obj;//赋值时需要使用被代理类对象赋值
    public void bind(Object obj){
        this.obj=obj;
    }
    //当我们通过代理类的对象调用方法a时，就会自动调用如下的方法：invoke()
    //将被代理类要执行的方法a的功能声明在invoke()中
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //method即为代理类对象调用的方法，此方法也就作为了被代理类对象要调用的方法
        //obj：被代理的对象
        Object returnValue = method.invoke(obj, args);
        //上述方法的返回值就作为invoke()的返回值
        return returnValue;
    }
}
public class ProxyTest {
    public static void main(String[] args) {
        SuperMan superMan=new SuperMan();
        //proxyInstance:代理类对象
        Human proxyInstance = (Human)ProxyFactory.getProxyInstance(superMan);
        //当通过代理类对象调用方法时，会自动调用被代理类中同名的方法
        String belief = proxyInstance.getBelief();
        System.out.println(belief);
        proxyInstance.eat("hotpot");
    }
}
