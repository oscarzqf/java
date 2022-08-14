package com.zqf.webservlet; /**
 * @description
 * @author oscarzqf
 * @create 2021-10-23-10:32
 */

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class ForwardC extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("跳转到了frowardC");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/a/b/c.html");
        requestDispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
