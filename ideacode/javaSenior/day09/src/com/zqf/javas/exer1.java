package com.zqf.javas;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author oscarzqf
 * @description
 * @create 2021-08-29-10:15
 */
public class exer1 {
 public void test(){
     Properties pros=new Properties();
     InputStream is=null;
     try {
         ClassLoader classLoader=exer1.class.getClassLoader();
         //配置文件默认为当moudle下的src中
         is=classLoader.getResourceAsStream("jdbc1.properties");
         pros.load(is);
         String user=pros.getProperty("user");
         String password=pros.getProperty("password");
     } catch (IOException e) {
         e.printStackTrace();
     } finally {
         try {
             if(is!=null)
             is.close();
         } catch (IOException e) {
             e.printStackTrace();
         }
     }

 }
}
