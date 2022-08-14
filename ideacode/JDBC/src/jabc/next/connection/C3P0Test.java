package jabc.next.connection;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;
import org.junit.Test;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author oscarzqf
 * @description
 * @create 2021-09-28-20:40
 */
public class C3P0Test {
    @Test
    public void testGetConnection() throws Exception {
        //获取C3P0数据库连接池-----方式一
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setDriverClass( "com.mysql.jdbc.Driver" ); //loads the jdbc driver
        cpds.setJdbcUrl( "jdbc:mysql://localhost:3306/test?characterEncoding=utf8&amp;" +
                "?rewriteBatchedStatements=true" );
        cpds.setUser("root");
        cpds.setPassword("100861");
        //通过设置相关的参数，对数据库连接池进行管理,
        //设置初始时数据库连接池中的连接数
        cpds.setInitialPoolSize(10);
        Connection conn = cpds.getConnection();
        System.out.println(conn);
        //销毁数据库连接池
       // DataSources.destroy(cpds);
    }
    //使用配置文件
    @Test
    public void testGetConnection1() throws Exception {
        ComboPooledDataSource cpds = new ComboPooledDataSource("helloc3p0");
        Connection conn = cpds.getConnection();
        System.out.println(conn);
    }
}
