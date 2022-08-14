package com.zqf.webservlet; /**
 * @description
 * @author oscarzqf
 * @create 2021-10-23-9:45
 */

import com.alibaba.druid.sql.dialect.oracle.ast.stmt.OracleSelectSubqueryTableSource;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class Servlet1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求的参数查看
        String username = request.getParameter("username");
        System.out.println("在servlet1中查看材料："+username);
        //给材料盖章，并传递到servlet2去查看
        request.setAttribute("key1","盖章了");
        //问路：servlet2（柜台2）怎么走
        /**
         * 请求转发必须要以斜杠打头，/ 斜杠表示地址为：http://ip:port/工程名/ ,
         * 映射到 IDEA 代码的 web 目录
         * */
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/servlet2");
        //走向servlet2程序（柜台2）
        requestDispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
