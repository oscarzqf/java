package com.zqf.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author oscarzqf
 * @description
 * @create 2021-08-19-19:41
 */
public class ReadData {
    public static void main(String[] args) {
        //读取用户的输入
        //方式一：使用Scanner实现，调用next()返回一个字符串
        //方式二：使用System.in实现。System.in--->转换流---->BufferedReader的readLine()
        BufferedReader br=null;
        try {
            InputStreamReader isr=new InputStreamReader(System.in);
            br=new BufferedReader(isr);
            System.out.println("请输入字符串：");
            br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(br!=null)
                    br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
