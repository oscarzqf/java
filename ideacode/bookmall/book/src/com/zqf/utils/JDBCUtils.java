package com.zqf.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.DbUtils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author oscarzqf
 * @description
 * @create 2021-10-23-16:31
 */
public class JDBCUtils{
    /**
     * 获取数据库连接池中的连接
     * @return
     */
    private static DruidDataSource dataSource=null;
    static {
        try {
            Properties pros = new Properties();
            InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            pros.load(is);
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(pros);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("successconnection");
    }

    /**
     * 获取连接失败就抛异常
     * @return
     */
    public static Connection getConnection() throws Exception {
        Connection connection = dataSource.getConnection();
        return connection;
    }
    /**
     * 关闭数据库连接，放回数据库连接池
     *
     */
    public static void close(Connection conn){
        try {
            DbUtils.close(conn);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
