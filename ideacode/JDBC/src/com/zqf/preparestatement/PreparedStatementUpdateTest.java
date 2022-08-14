package com.zqf.preparestatement;

import com.zqf.sqlconnect.Connectionss;
import com.zqf.util.JDBCUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;

/**
 * @author oscarzqf
 * @description
 * 使用PreparedStatement来替换Statement,实现对数据表的增删改查
 * 增删改;查
 * @create 2021-09-19-15:33
 */
public class PreparedStatementUpdateTest {
    //向customers表中添加一条数据
    @Test
    public void connecttestfinal()  {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //1.读取配置文件中的4个基本信息
            InputStream rs =ClassLoader.getSystemClassLoader()
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
            conn = DriverManager.getConnection(url, user, password);

            //4.预编译sql语句,返回PreparedStatement的实例
            String sql="insert into customers(name,email,birth) values(?,?,?)";//?为占位符
            ps = conn.prepareStatement(sql);
            //5.填充占位符
            ps.setString(1,"吴亦凡");
            ps.setString(2,"2283@qq.com");
            //格式化日期
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
            //字符串-->util下日期
            java.util.Date date = sdf.parse("2000-01-01");
            //util下日期-->sql下日期
            ps.setDate(3,new Date(date.getTime()));

            //6.执行操作
            ps.execute();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            //7.资源关闭
            try {
                if(ps!=null)
                ps.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                if(conn!=null)
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
    @Test
    //修改操作
    public void testUpdate() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //1.获取数据库连接
            conn = JDBCUtils.getConnection();
            //2.预编译sql语句，返回PrepareStatement的实例
            String sql="update customers set name=? where id=?";
            ps = conn.prepareStatement(sql);
            //3.填充占位符
            ps.setString(1,"吴签");
            ps.setInt(2,20);
            //4.执行
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //5.资源的关闭
            JDBCUtils.closeResource(conn,ps);
        }
    }
    @Test
    //使用工具类中通用的方法实现增删改
    public void testUpdateCommon(){
       // String sql="delete from customers where id=?";
        //update(sql,13);
        String sql="update `order` set order_name=? where order_id=?";
        JDBCUtils.update(sql,"BB",2);
    }
  }

