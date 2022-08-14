package com.zqf.file;

import java.io.*;

/**
 * @author oscarzqf
 * @description
 * @create 2021-08-19-16:45
 */
public class ImagesScret {
    public static void main(String[] args) {
        BufferedInputStream bis=null;
        BufferedOutputStream bos=null;
        try {
            bis=new BufferedInputStream(new FileInputStream(new File("head4.jpeg")));
            bos=new BufferedOutputStream(new FileOutputStream(new File("head5.jpeg")));
            int data;
            while((data=bis.read())!=-1){
                bos.write((byte)(data^5));//加密图片
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(bis!=null)
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(bis!=null)
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
