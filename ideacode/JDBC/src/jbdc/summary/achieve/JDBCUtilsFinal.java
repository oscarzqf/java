package jbdc.summary.achieve;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.DbUtils;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author oscarzqf
 * @description
 * JDBC使用过程总结；
 * 1.加载相应的数据库驱动到当前工程(lib)。
 * 2.加载数据库连接池到当前工程(lib)。
 * 3.根据使用的数据库连接池的文档创建配置文件.properties,
 * url,driverClassName与加载的数据库驱动有关，username,password为数据库用户信息
 * 4.创建数据库连接池，传入配置文件流；使用静态代码块，避免多次创建
 * 5.加载dbutils驱动，使用QueryRunner完成增删改查操作，落实到DAO中
 * 6.关闭资源，自定义或者使用DBUtils工具类关闭
 *
 *
 * 考虑数据库事务以后的数据库操作总结
 * 1.获取连接----使用数据库连接池
 * Connection conn=JDBCUtils.getConnectionByDruid();
 * conn.setAutoCommit(false);
 * 2.如下的多个DML操作，作为一个事务出现--自定义操作或使用dbutils，DAO模式
 * 操作1
 * 操作2
 * 操作3...
 * conn.commit();
 * 3.如果出现异常
 * conn.rollback();
 * 4.关闭资源
 *
 * @create 2021-10-01-11:45
 */
public class JDBCUtilsFinal {
    /**
     * 1.使用druid数据库连接池技术获取连接
     * 需要创建.properties文件
     */
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
     *2. 使用DBUtils.jar中提供的DBUtils工具类，实现资源的关闭
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
