package com.zqf.file;

import java.io.*;

/**
 * @author oscarzqf
 * @description
 * @create 2021-08-19-15:52
 */
public class BufferTest {
    public static void main(String[] args) {
        BufferedReader bis1=null;
        BufferedWriter bis2=null;
        try {
            bis1=new BufferedReader(new FileReader(new File("hello1.txt")));
            bis2=new BufferedWriter(new FileWriter(new File("hello2.txt")));
            //复制的细节：读取、写入
            //方式一
            char[] buffer=new char[5];
            int len;
            while((len= bis1.read())!=-1){
                bis2.write(buffer,0,len);
            }
            //方式二：使用String
            String data;
            while((data=bis1.readLine())!=null){
                //方法1
                bis2.write(data+"\n");//data中不包含换行符
                //方法2
                bis2.write(data);
                bis2.newLine();//提供换行操作
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //资源关闭
            try {
                if(bis1!=null)
                bis1.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(bis2!=null)
                bis2.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
