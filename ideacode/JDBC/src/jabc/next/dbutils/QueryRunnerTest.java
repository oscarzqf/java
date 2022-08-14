package jabc.next.dbutils;

import com.zqf.bean.Customer;
import jabc.next.util.JDBCUtilsImprove;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.*;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Handler;

/**
 * @author oscarzqf
 * @description
 * commons-dbutils 是Apache 组织提供的一个开源的JDBC工具类库，封装了
 * 针对于数据库的增删改查操作
 *
 * API文档中的QueryRunner中查看各种方法
 * @create 2021-09-29-12:39
 */
public class QueryRunnerTest{
    @Test
    public void testInsert()  {
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtilsImprove.getConnectionByDruid();
            String sql="insert into customers(name,email,birth) values(?,?,?)";
            int insertConut = runner.update(conn, sql, "吴京", "123@qq.cn", "1997-09-08");
            System.out.println("添加了"+insertConut+"条数据");
            //连接池中的close()方法使连接从Busy到free
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtilsImprove.closeResource(conn);
        }
    }
    /*
    * BeanHander:是ResultSetHandler接口的实现类，用于封装表中的一条数据
    * */
    @Test
    public void testQuery1() throws Exception{
        QueryRunner runner = new QueryRunner();
        Connection conn = JDBCUtilsImprove.getConnectionByDruid();
        String sql="select name,email,birth from customers where id=?";
        BeanHandler<Customer>handler=new BeanHandler<>(Customer.class);
        Customer customer = runner.query(conn, sql, handler, 24);
        System.out.println(customer);
        JDBCUtilsImprove.closeResource(conn);
    }
    /*
    * BeanListHandler:是ResultSetHandler接口的实现类,
    * 用于封装表中的多条数据构成的集合
    * */
    @Test
    public void testQuery2() throws Exception{
        QueryRunner runner = new QueryRunner();
        Connection conn = JDBCUtilsImprove.getConnectionByDruid();
        String sql="select name,email,birth from customers where id>=?";
        BeanListHandler<Customer>handler=new BeanListHandler<>(Customer.class);
        List<Customer> list = runner.query(conn, sql, handler, 21);
        list.forEach(System.out::println);
        JDBCUtilsImprove.closeResource(conn);
    }
    /*
    * MapHandler:是ResultSetHandler接口的实现类，用于将表中一条记录
    * 字段--key, 相应值--value，构成map返回
    * */
    @Test
    public void testQuery3() throws Exception{
        QueryRunner runner = new QueryRunner();
        Connection conn = JDBCUtilsImprove.getConnectionByDruid();
        String sql="select name,email,birth from customers where id=?";
        MapHandler handler = new MapHandler();
        Map<String, Object> map = runner.query(conn, sql, handler, 21);
        System.out.println(map);
        JDBCUtilsImprove.closeResource(conn);
    }
    /*
    * MapListHandler:是ResultSetHandler接口的实现类,
    * 返回多个记录对应map构成的list
    *
    * */
    @Test
    public void testQuery4() throws Exception{
        QueryRunner runner = new QueryRunner();
        Connection conn = JDBCUtilsImprove.getConnectionByDruid();
        String sql="select name,email,birth from customers where id>=?";
        MapListHandler handler=new MapListHandler();
        List<Map<String, Object>> list = runner.query(conn, sql, handler, 21);
        list.forEach(System.out::println);
        JDBCUtilsImprove.closeResource(conn);
    }
    /*
    * ScalarHandler:是ResultSetHandler接口的实现类,用于查询特殊值
    * */
    @Test
    public void testQuery5() throws Exception{
        QueryRunner runner = new QueryRunner();
        Connection conn = JDBCUtilsImprove.getConnectionByDruid();
        String sql="select count(*) from customers";
        ScalarHandler handler = new ScalarHandler();
        Long count =(Long) runner.query(conn, sql, handler);
        System.out.println(count);
        JDBCUtilsImprove.closeResource(conn);
    }


    /*
    * 自定义ResultSetHandler接口的实现类
    * */
    @Test
    public void testQuery6() throws Exception{
        QueryRunner runner = new QueryRunner();
        Connection conn = JDBCUtilsImprove.getConnectionByDruid();
        String sql="select name,email,birth from customers where id=?";
        ResultSetHandler<Customer>handler=new ResultSetHandler<Customer>() {
            @Override
            public Customer handle(ResultSet resultSet) throws SQLException {
                return null;//此方法的返回值就是查询结果
            }
        };
        Customer customer = runner.query(conn, sql, handler, 24);
        System.out.println(customer);
        JDBCUtilsImprove.closeResource(conn);
    }


}
