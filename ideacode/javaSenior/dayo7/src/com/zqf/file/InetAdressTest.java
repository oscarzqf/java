package com.zqf.file;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author oscarzqf
 * @description
 * @create 2021-08-20-19:16
 */
public class InetAdressTest {
    public static void main(String[] args) {
        try {
            InetAddress inte1=InetAddress.getByName("192.168.10.14");
            System.out.println(inte1);
            InetAddress inte2=InetAddress.getByName("www.sina.com");
            System.out.println(inte2);
            //获取本地IP
            InetAddress inte3=InetAddress.getByName("127.0.0.1");
            InetAddress inte4=InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
