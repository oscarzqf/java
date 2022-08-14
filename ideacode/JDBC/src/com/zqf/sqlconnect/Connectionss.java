package com.zqf.sqlconnect;
import org.junit.Test;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
/**
 * @author oscarzqf
 * @description
 * @create 2021-09-19-10:40
 */

public class Connectionss {
        //连接方式一：
         @Test
        public void connecttest() throws SQLException {
             //1.获取Driver实现类对象
            Driver driver=new com.mysql.jdbc.Driver();
            //2.获取连接路径
            //jdbc:mysql:协议
            //localhost:ip地址
            //3306：默认的mysql的端口号
            //test:数据库名
             //备注：mysql8会报错，网上查，好像是加时区
            String url="jdbc:mysql://localhost:3306/test";
            //3.登录信息
            //将用户名和密码封装在properties中
            Properties info=new Properties();
            info.setProperty("user","root");
            info.setProperty("password","100861");
            //4.连接登录
            Connection conn=driver.connect(url,info);
             System.out.println(conn);
        }
        @Test
        //方式二：对方式一进行迭代:在如下的程序中不出现第三方的API,
        //使得程序具有更好的可移植性
        public void connecttest2() throws Exception {
            //1.获取Driver实现类对象,使用反射
            Class clazz = Class.forName("com.mysql.jdbc.Driver");
            Driver driver = (Driver) clazz.newInstance();
            //2.提供要连接的数据库
            String url="jdbc:mysql://localhost:3306/test";
            //3.提供连接需要的用户名和密码
            Properties info=new Properties();
            info.setProperty("user","root");
            info.setProperty("password","100861");
            //4.获取连接
            Connection conn=driver.connect(url,info);
            System.out.println(conn);
        }
        @Test
        //方式三：使用DriverManager来替换Driver
    public void connecttest3() throws Exception {
             //1.获取Driver实现类对象
         Class clazz = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) clazz.newInstance();
        //2.提供另外三个基本信息
        String url="jdbc:mysql://localhost:3306/test";
        String user="root";
        String password="100861";
        //注册驱动
        DriverManager.registerDriver(driver);
        Connection conn=DriverManager.getConnection(url,user,password);
        System.out.println(conn);
    }
    @Test
    //方式四：加载驱动不显式注册
    public void connecttest4() throws Exception {
        //1.提供另外三个基本信息
        String url="jdbc:mysql://localhost:3306/test";
        String user="root";
        String password="100861";
        //2.加载驱动
        //Driver类中有一个静态代码块，随类的加载自动帮我们完成注册
        /*    static {
        try {
            DriverManager.registerDriver(new Driver());
        } catch (SQLException var1) {
            throw new RuntimeException("Can't register driver!");
            }
        }
        */
        //同时加载也可以省略,但是为了通用性，尽量别省略
        Class clazz = Class.forName("com.mysql.jdbc.Driver");
        //3.获取连接
        Connection conn=DriverManager.getConnection(url,user,password);
        System.out.println(conn);
    }
    @Test
    //方式五：最终版,将数据库连接需要的4个基本信息声明在配置文件中
    /*
    * 优点：
    * 1.实现了数据与代码的分离，实现了解耦
    * 2.如果需要修改配置文件信息，可以避免对程序进行重新打包
    * */
    //通过读取配置文件的方式，获取连接
    public void connecttestfinal() throws Exception {
        //1.读取配置文件中的4个基本信息
        InputStream rs = Connectionss.class.getClassLoader()
                .getResourceAsStream("jdbc.properties");
        Properties pros=new Properties();
        pros.load(rs);
        String user = pros.getProperty("user");
        String password = pros.getProperty("password");
        String url = pros.getProperty("url");
        String driverClass = pros.getProperty("driverClass");
        //2.加载驱动
        Class clazz = Class.forName(driverClass);
        //3.获取连接
        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println(conn);
    }

}
