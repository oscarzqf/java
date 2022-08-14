package com.zqf.socket;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author oscarzqf
 * @description
 * @create 2021-08-21-18:10
 */
public class URLTest {
    public static void main(String[] args) {
        InputStream ins=null;
        FileOutputStream fos=null;
        HttpURLConnection urlConnection=null;
        try {
            URL url=new URL("https://www.jd.com/?source=enterprise&cu=true&utm_source=browser.lenovo.com.cn&utm_medium=tuiguang&utm_campaign=t_330412191_&utm_term=3903cae6d98341c6b4d1191505996322");
          //连接
           urlConnection=(HttpURLConnection)url.openConnection();
            urlConnection.connect();
            ins = urlConnection.getInputStream();
           fos=new FileOutputStream("URLTest.html");
            byte[] buffer=new byte[1024];
            int len;
            while((len=ins.read(buffer))!=-1){
                fos.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                if(ins!=null)
                ins.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(fos!=null)
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //断开连接
            if(urlConnection!=null)
            urlConnection.disconnect();
        }

    }

}
