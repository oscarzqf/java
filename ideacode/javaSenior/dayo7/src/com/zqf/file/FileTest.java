package com.zqf.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.PublicKey;

/**
 * @author oscarzqf
 * @description
 * @create 2021-08-18-17:24
 */
public class FileTest {
    public static void main(String[] args) {
        //******将文件内容读入内存并输出
        //1.read()的理解:返回读入的一个字符。如果到达文件末尾，返回-1
        //2.异常的处理：为了保证流资源一定可以执行关闭操作。需要使用try-catch-finally处理
        //3.读入的文件一定要存在，否则报FileNotFoundException。
        //①实例化File类的对象，指明操作的文件
        File file=new File("hello.txt");
        //②提供具体的流
        FileReader fr=null;
        try {
            fr = new FileReader(file);
            //③数据的读入
            //read():返回读入的一个字符。如果到达文件末尾，返回-1
            int data;
            while((data=fr.read())!=-1){
                System.out.print((char)data);
            }
            //对read()操作升级：使用read的重载方法
            //read(char[]cubf):返回每次读入cbuf数组中的字符的个数。如果到文件末尾，返回-1
            char[] cbuf=new char[5];
            int len;
            while((len=fr.read(cbuf))!=-1){
               //方式一
                for(int i=0;i<len;++i){
                    System.out.println(cbuf[i]);
                }
                //方式二
                String str=new String(cbuf,0,len);
                System.out.print(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //④流的关闭操作
            try {
                if(fr!=null)
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
