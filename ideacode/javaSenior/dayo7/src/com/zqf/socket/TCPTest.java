package com.zqf.socket;

import com.sun.net.httpserver.Authenticator;
import org.junit.Test;

import java.io.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;

/**
 * @author oscarzqf
 * @description
 * @create 2021-08-21-15:18
 */
public class TCPTest {
    @Test
    public void client1() {
        Socket socket = null;
        OutputStream os = null;
        FileInputStream file=null;
        InputStream ins1=null;
        try {
            //创建socket()并设置接受方ip与端口号
            socket = new Socket(InetAddress.getByName("127.0.0.1"), 9090);
            //获取输出流
            os = socket.getOutputStream();
            //要发送的图片
           file = new FileInputStream("head.jpeg");
            //读取并发送
            byte[] buffer = new byte[1024];
            int len;
            while ((len = file.read(buffer)) != -1) {
                os.write(buffer, 0, len);
            }
            //传递发送完毕信号，避免服务器端一直读取并等待，read()阻塞
            socket.shutdownOutput();
            //接受客户端反馈
            //ByteArrayOutputStream内置可变数组，可避免乱码
            ByteArrayOutputStream baos=new ByteArrayOutputStream();
            ins1=socket.getInputStream();
            byte[] buffers=new byte[10];
            int len1;
            while((len1=ins1.read(buffers))!=-1){
                baos.write(buffers,0,len1);
            }
            System.out.println(baos.toString());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            try {
                if(file!=null)
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
            try {
                if(ins1!=null)
                ins1.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Test
    public void servers(){

        ServerSocket ss= null;
        Socket socket= null;
        InputStream is= null;
        FileOutputStream fileo=null;
        OutputStream os1=null;
        try {
            ss = new ServerSocket(9090);
            socket = ss.accept();
            is = socket.getInputStream();
             fileo=new FileOutputStream("headll.jpeg",true);

            byte[] buffer=new byte[1024];
            int len;
            while((len=is.read(buffer))!=-1){
                fileo.write(buffer,0,len);
            }
            //反馈给客户端
           os1=socket.getOutputStream();
            os1.write("服务器已经收到".getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                if(fileo!=null)
                fileo.close();
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
            try {
                if(os1!=null)
                os1.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
