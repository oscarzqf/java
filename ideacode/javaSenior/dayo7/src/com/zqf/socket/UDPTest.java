package com.zqf.socket;

import org.junit.Test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

/**
 * @author oscarzqf
 * @description
 * @create 2021-08-21-16:45
 */
public class UDPTest {
    //发送端
    @Test
    public void sender(){
        DatagramSocket socket= null;
        try {
            socket = new DatagramSocket();
            String str="我是UDP方式发送的";
            byte[] data=str.getBytes(StandardCharsets.UTF_8);
            InetAddress inet=InetAddress.getLocalHost();
            DatagramPacket packet=new DatagramPacket(data,0,data.length,inet,8888);
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }

    }
    //接收端
    @Test
    public void receive(){
        DatagramSocket socket= null;
        try {
            socket = new DatagramSocket(8888);
            byte[] buffer=new byte[100];
            DatagramPacket packet=new DatagramPacket(buffer,0,buffer.length);
            socket.receive(packet);
            System.out.println(new String(packet.getData(),0, packet.getLength()));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }

    }
}
