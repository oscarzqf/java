package com.zqf.web;

import com.zqf.pojo.User;
import com.zqf.service.UserService;
import com.zqf.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author oscarzqf
 * @description
 * @create 2021-11-30-17:25
 */
public class LoginServlet extends HttpServlet {
    private UserService userService=new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //调用userService.login()方法处理登录
        if(userService.login(new User(null,username,password,null))!=null){
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
            System.out.println("登录成功");
        }else{
            req.setAttribute("msg","用户或密码错误");
            req.setAttribute("username",username);
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            System.out.println("登录失败");
        }
    }
}
