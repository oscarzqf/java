package com.zqf.file;

import java.io.*;

/**
 * @author oscarzqf
 * @description
 * @create 2021-08-19-17:55
 */
public class InputOutputStreamRW {
    public static void main(String[] args) {
        //解码、编码（gbk）
        InputStreamReader ir=null;
        OutputStreamWriter irs=null;
        try {
            FileInputStream fin=new FileInputStream("hello.txt");
            FileOutputStream fins=new FileOutputStream("hello-gbk.txt");
            //InputStreamReader ir=new InputStreamReader(fin);//使用系统默认字符集
            //参数二指明了字符集，具体使用哪个字符集，取决于文件保存时使用的字符集
            ir=new InputStreamReader(fin,"UTF-8");
            irs=new OutputStreamWriter(fins,"gbk");
            char[] buffer=new char[5];
            int len;
            while ((len=ir.read(buffer))!=-1){
                irs.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(ir!=null){
                    ir.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(irs!=null)
                irs.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
