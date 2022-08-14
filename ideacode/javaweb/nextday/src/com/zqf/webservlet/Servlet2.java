package com.zqf.webservlet; /**
 * @description
 * @author oscarzqf
 * @create 2021-10-23-9:46
 */

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class Servlet2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求的参数（办事的材料）查看
        String username = request.getParameter("username");
        System.out.println("servlet2查看材料："+username);
        //查看servlet1是否盖章
        Object key1 = request.getAttribute("key1");
        System.out.println("servlet2查看是否有章"+key1);
        //处理自己的事务
        System.out.println("servlet2处理自己业务");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
