package com.zqf.jsp;



import org.apache.commons.io.IOUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

/**
 * @author oscarzqf
 * @description
 * @create 2021-12-04-16:09
 */
public class Download extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取要下载的文件名
        String downloadFileName="a.jpg";
        //2.读取要下载的文件内容（通过ServletContext对象可以读取）
        ServletContext servletContext = getServletContext();
        //获取要下载的文件的类型
        String mimeType = servletContext.getMimeType("/file/" + downloadFileName);
        //3.在回传前，通过响应头告诉客户端返回数据的类型
        resp.setContentType(mimeType);
        //4.还要告诉客户端收到的数据是用于下载的（使用响应头）
        //attachment表示下载使用，filename=表示指定下载的文件名,
        //也就是给文件指定名字，可以与源文件名相同也可以不同。
        String str = "attachment; fileName=" + URLEncoder.encode("中文.jpg", "UTF-8");
        resp.setHeader("Content-Disposition",str);
        //  /被服务器解析为表示地址为http://ip:port/工程名/  映射到web目录
        InputStream resourceAsStream = servletContext.getResourceAsStream("/file/" + downloadFileName);
        //获取响应的输出流
        OutputStream outputStream=resp.getOutputStream();
        //5.使用jar包来读取流中的所有数据给输出流,输出到客户端
        IOUtils.copy(resourceAsStream,outputStream);
    }
}
