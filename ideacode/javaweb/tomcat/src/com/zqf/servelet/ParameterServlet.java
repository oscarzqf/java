package com.zqf.servelet; /**
 * @description
 * @author oscarzqf
 * @create 2021-10-23-9:13
 */

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class ParameterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求的参数
        //获取单个值
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //获取多个值
        String[] hobbies = request.getParameterValues("hobby");
        System.out.println(username);
        System.out.println(password);
        for (String hobby : hobbies) {
            System.out.println(hobby);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
