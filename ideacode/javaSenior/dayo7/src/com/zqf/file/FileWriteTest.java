package com.zqf.file;

import jdk.jfr.events.FileReadEvent;
import jdk.jfr.events.FileWriteEvent;

import java.io.*;

/**
 * @author oscarzqf
 * @description
 * @create 2021-08-19-12:26
 */
public class FileWriteTest {
    public static void main(String[] args) {
        for (int i = 0; i < 5000; ++i) {
            //测试字节流，FileOutputStream ，FileInputStream使用
            //①创建File对象，指明读入和写出的文件
            File file = new File("cortoon.jpg");
            File file1 = new File("cortoon1.jpg");
            //②提供输入输出流对象
            FileInputStream fw = null;
            FileOutputStream fw1 = null;
            try {
                //③读取写入进行复制
                fw = new FileInputStream(file);
                fw1 = new FileOutputStream(file1, true);
                byte[] sbuf = new byte[100];
                int len;
                while ((len = fw.read(sbuf)) != -1) {
                    fw1.write(sbuf, 0, len);
                }
                System.out.println();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                //④流资源的关闭
                try {
                    if (fw != null)
                        fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    if (fw1 != null)
                        fw1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
