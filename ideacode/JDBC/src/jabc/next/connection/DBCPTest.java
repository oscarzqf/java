package jabc.next.connection;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author oscarzqf
 * @description
 *              测试DBCP数据库连接池技术
 * @create 2021-09-29-8:58
 */
public class DBCPTest {
    @Test
    //方式1
    public  void  testDBCPcONNECTION() throws SQLException {
        //创建了DBCP数据库连接池
        BasicDataSource source=new BasicDataSource();
        //设置基本的信息
        source.setDriverClassName("com.mysql.jdbc.Driver");
        source.setUrl("jdbc:mysql://localhost:3306/test?characterEncoding=utf8&amp;?rewriteBatchedStatements=true");
        source.setUsername("root");
        source.setPassword("100861");
        //设置其他涉及数据库连接池管理的相关属性
        source.setInitialSize(10);
        //连接
        Connection conn = source.getConnection();
        System.out.println(conn);
    }

    @Test
    //方式二：推荐使用配置文件
    public void  testDBCPcONNECTION1() throws Exception {
        Properties pros = new Properties();
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("dbcp.properties");
        pros.load(is);
        DataSource source = BasicDataSourceFactory.createDataSource(pros);
        Connection conn = source.getConnection();
    }
}
