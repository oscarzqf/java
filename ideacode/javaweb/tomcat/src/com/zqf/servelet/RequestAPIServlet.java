package com.zqf.servelet; /**
 * @description
 * @author oscarzqf
 * @create 2021-10-22-22:30
 */

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.DataOutput;
import java.io.IOException;

public class RequestAPIServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //i. getRequestURI() 获取请求的资源路径(工程名/标签地址)
        System.out.println("uri---"+ request.getRequestURI());
        //ii. getRequestURL() 获取请求的统一资源定位符（绝对路径）
        System.out.println("url---"+request.getRequestURL());
        //iii. getRemoteHost() 获取客户端的 ip 地址
        /**
         * 在 IDEA 中，使用 localhost 访问时，得到的客户端 ip 地址是 ===>>> 127.0.0.1<br/>
         * 在 IDEA 中，使用 127.0.0.1 访问时，得到的客户端 ip 地址是 ===>>> 127.0.0.1<br/>
         * 在 IDEA 中，使用 真实 ip 访问时，得到的客户端 ip 地址是 ===>>> 真实的客户端 ip 地址<br/>
         */
        System.out.println("ip--"+request.getRemoteHost());
        //iv. getHeader() 获取请求头
        System.out.println("请求头"+request.getHeader("User-Agent"));
        //vii. getMethod() 获取请求的方式 GET 或 POS
        System.out.println(request.getMethod());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
