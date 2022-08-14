package jabc.next.transaction;
import com.zqf.bean.User;
import com.zqf.util.JDBCUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;

/**
 * @author oscarzqf
 * @description
 *          1.数据库事务：一组逻辑单元，使数据从一种状态到另一种状态
 *              >一组逻辑操作单元，一个或多个DML操作
 *          2.数据处理的原则：保证所有事务都作为一个工作单元来执行，即使出现了故障，都不能改变这种执行方式。
 *          当在事务中执行多个操作时，要么所有的事务都被提交(commit),那么这些修改就会永久的保存下来；
 *          要么数据库管理系统将放弃所做的所有的修改，整个事务回滚(roolback)到最初的状态
 *          3.数据一旦被提交，就不可回滚
 *          4.哪些操作会导致数据的自动提交？
 *          >DDL操作一旦执行，都会自动提交
 *              >set autocommit=false 对DDL操作失效
 *          >DML默认情况下，一旦执行，就会自动提交
 *              >我们可以通过set autocommit =false 的方式取消对DML操作的自动提交。
 *          >默认在关闭连接时，会自动提交数据。
 * @create 2021-09-24-10:54
 */
public class TransactionTest{
    @Test
    /**未考虑数据库事务的转账操作，出现异常无法回滚*****/
    public void testConnection(){
        String sql="update user_table set balance=balance-100 where user=?";
        JDBCUtils.update(sql,"AA");
        System.out.println(10/0);//模拟网络异常
        String sql1="update user_table set balance=balance+100 where user=?";
        JDBCUtils.update(sql1,"BB");
    }
    @Test
    /**考虑数据库事务的转账操作**/
    public void testUpdateWithTS(){
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            //1.取消数据的自动提交
            conn.setAutoCommit(false);
            String sql="update user_table set balance=balance-100 where user=?";
            updateTS(conn, sql,"AA");
            //模拟网络异常
            //System.out.println(10/0);
            String sql1="update user_table set balance=balance+100 where user=?";
            updateTS(conn,sql1,"BB");

            System.out.println("转账成功！");
            //2.提交数据
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            //3.回滚数据
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        } finally {
            //关闭之前恢复本次连接的自动提交为默认，
            // 主要针对数据库连接池时使用
            try {
                conn.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            JDBCUtils.closeResource(conn,null);
        }
    }
    /**通用的增删改2.0----考虑事务
     * 思想就是一个连接做多个sql操作
     * 于是将一个连接作为参数串联多个操作
     * 出现异常就回滚
     * */
    public static int updateTS(Connection conn,String sql,Object...args){
        PreparedStatement ps = null;
        try {
            //1.预编译sql语句，返回PrepareStatement的实例,预编译已经确定了sql格式，后续填入是数据
            ps = conn.prepareStatement(sql);
            //2.填充占位符
            for(int j = 0;j<args.length;++j){
                ps.setObject(j+1,args[j]);
            }
            //3.执行，此方法会返回增删改影响行数

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //4.资源的关闭
            JDBCUtils.closeResource(null,ps);
        }
        return 0;//执行失败返回0
    }

    /**
     * 通用查询getForList2.0
     * 实现针对于不同表的通用查询操作，返回表中的多条数据----考虑事务
     * @param clazz  表的运行时类
     * @param <T>   泛型
     */
    public  <T> ArrayList<T> getForList(Connection conn,Class<T> clazz, String sql, Object...args){
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            resultSet = ps.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            //创建集合对象存储数据
            ArrayList<T> list  = new ArrayList<>();
            while(resultSet.next()){
                //存储每一行数据
                T t = clazz.newInstance();
                for (int i = 0; i < columnCount; ++i) {
                    Object columnValue = resultSet.getObject(i + 1);
                    //获取列的别名:再根据别名获取属性，使用反射
                    String columnLabel = metaData.getColumnLabel(i + 1);
                    Field declaredField = clazz.getDeclaredField(columnLabel);
                    declaredField.setAccessible(true);
                    declaredField.set(t, columnValue);//方法的参数本就为object类型
                }
                list.add(t);//处理完一个对象后放入集合
            }
            //返回集合
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jabc.next.util.JDBCUtils.closeResource(null, ps, resultSet);
        }
        return null;
}
/*************** 事务的隔离级别举例    *****/
        @Test
        public void testTransactionSelect() throws Exception {
            Connection conn = JDBCUtils.getConnection();
            //获取当前连接的隔离级别
            System.out.println(conn.getTransactionIsolation());
            //设置当前连接的隔离级别
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            //取消自动提交
            conn.setAutoCommit(false);
            String sql="select user,password,balance from user_table where user=?";
            User user = getSingleDate(conn, User.class, sql, "CC");
            System.out.println(user);
        }
        @Test
        public void testTransactionUpdate() throws Exception {
            Connection conn = JDBCUtils.getConnection();
            String sql="update user_table set balance=? where user=?";
            //取消自动提交
            conn.setAutoCommit(false);
            updateTS(conn,sql,4000,"CC");
            Thread.sleep(15000);
            System.out.println("修改结束");

        }
    /**
     * @author oscarzqf
     * @description
     *   getSingleDate 2.0
     *   实现针对于不同表的通用查询操作，返回表中的一条数据--考虑事务
     * @create 2021-09-22-9:16
     */
    public static <T>  T getSingleDate(Connection conn,Class<T> clazz,String sql, Object...args){
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            resultSet = ps.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            if (resultSet.next()) {
                T t = clazz.newInstance();//通过反射创建对象
                for (int i = 0; i < columnCount; ++i) {
                    Object columnValue = resultSet.getObject(i + 1);
                    //获取列的别名:再根据别名获取属性，使用反射
                    String columnLabel = metaData.getColumnLabel(i + 1);
                    Field declaredField = clazz.getDeclaredField(columnLabel);
                    declaredField.setAccessible(true);
                    declaredField.set(t, columnValue);
                }
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            com.zqf.util.JDBCUtils.closeResource(conn, ps, resultSet);
        }
        return null;
    }

}
