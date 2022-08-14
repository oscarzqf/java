package com.zqf.web;

import com.alibaba.fastjson.JSON;
import com.sun.org.apache.xml.internal.serialize.EncodingInfo;
import com.zqf.pojo.images;
import sun.net.www.content.image.jpeg;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author oscarzqf
 * @description
 * @create 2022-03-25-20:58
 */
public class MessageTest extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        /* 允许跨域的主机地址 */
        response.setHeader("Access-Control-Allow-Origin", "*");
        /* 允许跨域的请求方法GET, POST, HEAD 等 */
        response.setHeader("Access-Control-Allow-Methods", "*");
        /* 重新预检验跨域的缓存时间 (s) */
        response.setHeader("Access-Control-Max-Age", "3600");
        /* 允许跨域的请求头 */
        response.setHeader("Access-Control-Allow-Headers", "*");
        /* 是否携带cookie */
        response.setHeader("Access-Control-Allow-Credentials", "true");
        //根据业务需求增加一些jdbc操作
        images[] imag=new images[9];
        for (int i = 0; i <9; i++) {
            imag[i]=new images(i+1,"https://cn.vuejs.org/","http://localhost:8081/book/static/img/0"+(i+1)+".jpeg");
        }
        String text1 = JSON.toJSONString(imag);//序列化对象，注意数组也是包装类，直接传
        PrintWriter writer=response.getWriter();
        writer.write(text1);
    }
}
