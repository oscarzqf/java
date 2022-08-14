package com.zqf.exer;

import java.util.Optional;
/**
 * @author oscarzqf
 * @description
 * @create 2021-08-31-22:32
 */
public class OptionalTest {
    public static void main(String[] args) {
        Girl girl=new Girl("001");
        //of(T t):创建一个Optional实例，t必须非空
        Optional<Girl> optionalGirl=Optional.of(girl);
        //ofNullable(T t):t可以为null
        Optional<Girl> optionalGirl1=Optional.ofNullable(girl);
        //orElse(T other):如果有值则返回，否则返回other，
        // 相当于设置一个默认值避免空指针
        Girl zahngjxj = optionalGirl1.orElse(new Girl("zahngjxj"));//一定非空
        System.out.println(zahngjxj.name);
    }
}
class Girl{
    String name;
    public Girl() {
    }
    public Girl(String name) {
        this.name = name;
    }
}