package com.zqf.exerObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * @author oscarzqf
 * @description
 * @create 2021-08-20-10:37
 */
public class ObjectStream {
  

    public static void main(String[] args) {
        //反序列化：将磁盘文件中的对象还原为内存中的一个java对象
        //使用ObjectInputStream来实现
        ObjectInputStream ois= null;
        try {
            ois = new ObjectInputStream(new FileInputStream("object.dat"));
            Object obj=ois.readObject();
            String str=(String)obj;
            System.out.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if(ois!=null)
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
