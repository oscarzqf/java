package com.zqf.web;

import com.zqf.pojo.User;
import com.zqf.service.UserService;
import com.zqf.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author oscarzqf
 * @description
 * @create 2021-11-29-13:02
 */
public class RegistServlet extends HttpServlet {

    private UserService userService=new UserServiceImpl();

    //遇到的两个重要的问题

    //1：在进行请求转发跳转页面时，如果无法跳转可能是前端js代码设置点击事件后返回 return false 导致表单无法执行提交
    //解决方法去掉return 语句
    //2.junit测试数据库连接正确，而使用tomcat运行时报错java.lang.NullPointerException: inStream parameter is null
    //原因：读取配置文件时使用的是系统类加载器改为当前类加载器时不报错正常连接。
    //查阅资料发现
    //
    //ClassLoader.getSystemClassLoader方法无论何时均会返回ApplicationClassLoader,其只加载classpath下的class文件。
    //
    //在javaSE环境下，一般javaSE项目的classpath为bin/目录，因此只要编译后的class文件在classpath下就可以。
    // 此时ApplicationClassLoader就可以加载动态生成的类。
    //
    //但在javaEE环境下，我们的项目里的类是通过WebAppClassLoader类来加载的，
    // 此时我们获取了ApplicationClassLoader，因此自然找不到class文件。
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");
        //2.检查验证码为abcde--先写为一个固定数
        if("abcde".equalsIgnoreCase(code)){
            //检查用户名是否可用
            if(userService.existUsername(username)){
                //不可用，跳转回注册页面
                System.out.println("用户名已存在");
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
            }else{
                //可用，调用service保存到数据库
                userService.registerUser(new User(null,username,password,email));
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
            }
        }else{
            //错误跳转回注册页面
            System.out.println("验证码错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }
    }
}
