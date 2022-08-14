package com.zqf.testExercise;

import com.zqf.util.JDBCUtils;

import java.util.Scanner;

/**
 * @author oscarzqf
 * @description
 * @create 2021-09-23-19:32
 */
public class MainTest {
    public static void main(String[] args) {
        Scanner scan =new Scanner(System.in);
        System.out.println("请输入用户名：");
        String s = scan.nextLine();
        System.out.println("请输入邮箱：");
        String s1 = scan.nextLine();
        System.out.println("请输入生日：");
        String s2 = scan.nextLine();//1992-09-08,使用这种格式会进行隐式转换
        String sql="insert into customers(name,email,birth) values(?,?,?)";
        int insertCount = JDBCUtils.update(sql, s, s1, s2);
        if(insertCount==0){
            System.out.println("添加失败");
        }else{
            System.out.println("添加成功");
        }
    }
}
