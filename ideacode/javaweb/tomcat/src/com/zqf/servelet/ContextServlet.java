package com.zqf.servelet; /**
 * @description
 * @author oscarzqf
 * @create 2021-10-22-9:00
 */

import javax.naming.Name;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class ContextServlet extends HttpServlet {
    @Override
    //默认调用get请求
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       //获取serletContext对象
        //方式1
        ServletContext context = getServletConfig().getServletContext();
        //方式2
        ServletContext context1 = getServletContext();
        // 1、获取 web.xml 中配置的上下文参数 context-param
        String username = context.getInitParameter("username");
        System.out.println(username);
        String password = context.getInitParameter("password");
        System.out.println(password);
        //  2、获取当前的工程路径，格式: /工程路径
        String path = context.getContextPath();
        System.out.println(path);
        //3、获取工程部署后在服务器硬盘上的绝对路径
        //  / 斜杠在服务器解析的时候，表示地址为：http://ip:port/工程路径/
        //  映射到IDEA的web目录
        String realPath = context.getRealPath("/");
        System.out.println(realPath);
        System.out.println("web工程下css目录的绝对路径是："+context.getRealPath("/css"));
        //4、像 Map 一样存取数据,只要创建全局都可以访问
        context1.setAttribute("key1","value1");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
