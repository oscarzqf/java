package jabc.next.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.apache.commons.dbutils.DbUtils;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author oscarzqf
 * @description
 * @create 2021-09-29-8:47
 */
public class JDBCUtilsImprove {
    //使用C3P0获取连接
    //保证池子只造一个，所以造池子放在外面
    private static ComboPooledDataSource cpds=
            new ComboPooledDataSource("helloc3p0");

    public static Connection getConnectionByCP() throws SQLException {
        Connection conn = cpds.getConnection();
        return conn;
    }

    //使用DBCP数据库连接池技术获取连接
    private static DataSource source =null;
    static{
        try {
            Properties pros = new Properties();
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("dbcp.properties");
            pros.load(is);
            //创建DBCP数据库连接池
            source = BasicDataSourceFactory.createDataSource(pros);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnectionByDBCP() throws Exception {
        Connection conn = source.getConnection();
        return conn;
    }

    //使用druid数据库连接池技术获取连接
    private static DataSource dataSource =null;
    static {
        try {
            Properties pros = new Properties();
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");
            pros.load(is);
            dataSource = DruidDataSourceFactory.createDataSource(pros);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnectionByDruid() throws Exception {
        Connection conn = dataSource.getConnection();
        return conn;
    }

    /**
     * 自定义资源关闭
     * @param conn
     */
    public static void closeResource(Connection conn){
        try {
            if(conn!=null)
                conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    /**
     * 使用DBUtils.jar中提供的DBUtils工具类，实现资源的关闭
     *
     */
    public static void closeResource1(Connection conn){
        try {
            DbUtils.close(conn);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
