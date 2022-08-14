package com.zqf.socket;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * @author oscarzqf
 * @description
 * @create 2021-08-21-12:53
 */
public class SockenTest {
    @Test
    public void client(){
        //1.创建Socket对象，指明服务器端的IP和端口号
        Socket socket= null;
        OutputStream os= null;
        try {
            InetAddress inet=InetAddress.getByName("127.0.0.1");
            socket = new Socket(inet,8899);
            //2.获取一个输出流，用于输出数据
            os = socket.getOutputStream();
            //3.写出数据的操作
            os.write("你好，我是客户端".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.资源的关闭
            try {
                if(os!=null)
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(socket!=null)
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Test
    public void server(){

        ServerSocket ss= null;
        Socket socket= null;
        InputStream is= null;
        ByteArrayOutputStream baos= null;
        try {
            //1.创建服务器端的ServerSocket,指明自己的端口号
            ss = new ServerSocket(8899);
            //2.调用accept()接受来自于客户端的socket
            socket = ss.accept();
            //3.获取输入流
            is = socket.getInputStream();
            //4.读取输入流中的数据
            //使用下面的类装全部数据，防止中文乱码
            baos = new ByteArrayOutputStream();
            byte[] buffer=new byte[5];
            int len;
            while((len=is.read(buffer))!=-1){
                baos.write(buffer,0,len);
            }
            System.out.println(baos.toString());
            System.out.println(socket.getInetAddress().getHostAddress());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //5.关闭资源
            try {
                if(baos!=null)
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(is!=null)
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(socket!=null)
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(ss!=null)
                ss.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
