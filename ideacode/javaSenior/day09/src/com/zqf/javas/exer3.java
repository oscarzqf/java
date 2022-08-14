package com.zqf.javas;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author oscarzqf
 * @description
 * @create 2021-08-29-13:56
 */
public class exer3 {
    @Test
    public  void test() throws Exception{
        Class<Person> clazz=Person.class;
        //创建运行时类对象
        Person person = clazz.newInstance();
        //获取指定的属性:要求运行时类属性为public的,通常不使用
        Field id=clazz.getField("id");
        //设置当前属性的值
        //set():参数1:指明设置哪个对象的属性 参数2:将此属性值设置为多少
        id.set(person,1001);
        //获取当前属性的值
        //get():参数1:获取哪个对象的当前属性值
        int pId=(int)id.get(person);
        System.out.println(pId);

        //掌握这种
        //1.getDeclaredField(String fieldName):获取运行时类中指定变量名的属性
        Field name = clazz.getDeclaredField("name");
        //2.保证当前属性是可访问的
        name.setAccessible(true);
        //3.获取、设置指定对象的此属性值
        name.set(person,"zhangqianfeng");

        //操作运行时类中的指定的方法--需要掌握
        //1.获取指定的某个方法
        //getDeclaredMethod(),参数1:指明获取的方法的名称  参数2：指明获取方法的形参列表
        Method show = clazz.getDeclaredMethod("show", String.class);
        //2.保证当前方法是可访问的
        show.setAccessible(true);
        //3.invoke():参数1：方法的调用对象  参数2:给方法形参赋值的实参
        // invoke()方法的返回值即为对应类中调用的方法的返回值
        String zhang =(String)show.invoke(person, "zhang");

        //如何调用静态方法，对象参数设置为null或者类.class
        Method show1 = clazz.getDeclaredMethod("show");
        show1.setAccessible(true);
        //如果没有返回值此时invoke()方法返回null
        show1.invoke(Person.class);

        //如何调用运行时类中指定的构造器（用的少）
        //1.获取指定的构造器
        //getDeclaredConstructor():参数:指明构造器的参数列表
        Constructor<Person> declaredConstructor = clazz.getDeclaredConstructor(String.class);
        //2.保证构造器是可访问的
        declaredConstructor.setAccessible(true);
        //3.调用此构造器创建运行时类对象
        Person person1 = declaredConstructor.newInstance("Tom");
    }
}
