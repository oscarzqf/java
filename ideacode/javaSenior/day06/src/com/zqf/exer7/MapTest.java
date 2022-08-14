package com.zqf.exer7;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * @author oscarzqf
 * @description
 * @create 2021-08-17-17:23
 */
public class MapTest {
    public static void main(String[] args) {

        FileInputStream fis=null;
        try {
            Properties pros=new Properties();
            fis =new FileInputStream("jdbc.properties");//默认使用工程下文件
            pros.load(fis);//加载流对应的文件
            String name = pros.getProperty("name");
            String password = pros.getProperty("password");
            System.out.println(name+"="+password);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }
}
