package com.zqf.javas;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;

/**
 * @author oscarzqf
 * @description
 * @create 2021-08-29-10:41
 */
public class exer2 {
    public static void main(String[] args) {
        try {
            //获取运行时类
            Class<Person> clazz=Person.class;
            //newInstance():调用此方法，创建对应的运行运行时类对象,
            // 内部调用运行时类的空参构造器（权限为非private）
            Person obj=clazz.newInstance();
            System.out.println(obj);


            //获取属性结构
            //getFields():获取当前运行时类及其父类中声明为public的属性
            Field[] fields=clazz.getFields();
            for(Field f1:fields){
                System.out.println(f1);
                //获取权限修饰符
                int modifiers = f1.getModifiers();
                System.out.println(Modifier.toString(modifiers));
                //获取数据类型
                Class type = f1.getType();
                System.out.println(type.getName());
                //获取变量名
                String fname=f1.getName();
            }
            //getDeclaredFields():获取当前运行时类中声明的所有属性（不包含父类中声明属性）
            Field[] declaredFields=clazz.getDeclaredFields();

            //获取方法
            //getMethods():获取当前运行时类及其所有父类中声明为public的方法
            Method[] methods = clazz.getMethods();
            //.getDeclaredMethods():获取当前运行时类中所有的方法，不包含父类
            Method[] declaredMethods = clazz.getDeclaredMethods();
            for(Method m:declaredMethods){
                //获取方法声明的注解
                Annotation[] annotations = m.getAnnotations();
                //获取权限修饰符
                int modifiers = m.getModifiers();
                System.out.println(Modifier.toString(modifiers));
                //返回值类型
                Class returnType = m.getReturnType();
                System.out.println(returnType.getName());
                //方法名
                System.out.println(m.getName());
                //获取形参列表
                Class[] parameterTypes = m.getParameterTypes();
                //抛出的异常,无异常exceptionTypes.length==0
                Class[] exceptionTypes = m.getExceptionTypes();

                //获取构造器结构
                //getConstructors()：获取当前运行时类中中声明为public的构造器
                Constructor[] constructors = clazz.getConstructors();
                //getDeclaredConstructors():获取当前运行时类中声明的所有的构造器
                Constructor[] declaredConstructors = clazz.getDeclaredConstructors();

                //获取运行时类的父类
                Class superclass = clazz.getSuperclass();
                //获取运行时类带泛型的父类
                Type genericSuperclass = clazz.getGenericSuperclass();
                ParameterizedType paramType=(ParameterizedType) genericSuperclass;
                //获取运行时类带泛型的父类的泛型类型
                Type[] actualTypeArguments = paramType.getActualTypeArguments();
                System.out.println(actualTypeArguments[0].getTypeName());

                //获取运行时类实现的接口
                Class[] interfaces = clazz.getInterfaces();
                //获取运行时类父类实现的接口
                Class[] interfaces1 = clazz.getSuperclass().getInterfaces();

                //获取运行时类所在的包
                Package aPackage = clazz.getPackage();

                //获取运行时类声明的注解
                Annotation[] annotations1 = clazz.getAnnotations();
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
