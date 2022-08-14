package com.zqf.servelet;

import com.alibaba.druid.sql.dialect.oracle.ast.stmt.OracleSelectSubqueryTableSource;
import org.apache.commons.logging.impl.ServletContextCleaner;
import org.apache.taglibs.standard.tag.common.sql.DataSourceUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author oscarzqf
 * @description
 * @create 2021-10-18-9:47
 */
public class HelloServlet implements Servlet {
    public HelloServlet(){
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init初始化方法");
        //1.可以获取Servlet程序的别名servlet-name值
        System.out.println("程序的别名是："+servletConfig.getServletName());
        //2.获取初始化参数 init-param
        System.out.println("username="+servletConfig.getInitParameter("username"));
        System.out.println("url="+servletConfig.getInitParameter("url"));
        //3.获取 ServletContext 对象
        System.out.println(servletConfig.getServletContext());
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    /**
     * service 方法是专门用来处理请求和响应的
     * @param servletRequest
     * @param servletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
       //向下类型转换为子类（因为它有getMethod()方法,可以得到是post还是get）
        HttpServletRequest httpServletRequest=(HttpServletRequest)servletRequest;
        String method = httpServletRequest.getMethod();
        if("get".equals(method)){
            //避免代码不好维护，写成方法调用
            doGet();
        }else if("post".equals(method)){
            doPost();
        }
    }
    public void doGet(){
        System.out.println("get请求");
    };
    public void doPost(){
        System.out.println("post请求");
    };

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
