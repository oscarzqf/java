package com.zqf.exer;

import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author oscarzqf
 * @description
 * @create 2021-08-31-12:43
 */
public class StreamAPITest {
    public static void main(String[] args) {
        List<Integer> list1=Arrays.asList(12,22,33,44,11,2,3,7,66);
        //collect(Collector c)--将流转换为其他形式。接收一个Collector
        //接口的实现，用于给Stream中元素做汇总的方法
        List<Integer> collect = list1.stream().filter(a -> a > 30)
                .collect(Collectors.toList());
        collect.forEach(System.out::println);

    }
}
