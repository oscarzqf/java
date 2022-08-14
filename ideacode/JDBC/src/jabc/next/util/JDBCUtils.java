package jabc.next.util;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

/**
 * @author oscarzqf
 * @description
 * 操作数据库的工具类使用PreparedStatement
 * PreparedStatement：
 * 1.解决Statement的拼串、sql注入问题，可以操作Blob类型的数据，而Statement做不到。
 * 2.PreparedStatement可以实现更高效的批量操作。
 *
 * @create 2021-09-19-16:13
 */
public class JDBCUtils {
    /**
     *1
     * @return  返回一个数据库连接
     * @throws Exception
     */
    public static Connection getConnection() throws Exception{
        //1.读取配置文件中的4个基本信息
        InputStream rs = ClassLoader.getSystemClassLoader()
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
        return conn;
    }

    /**
     * 2
     * 关闭资源的操作
     * @param conn
     * @param ps
     */
    public static void closeResource(Connection conn, Statement ps){
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
    /**
     * 3
     * 通用的增删改操作
     * sql中占位符个数与可变形参长度一样
     */
    public static int update(String sql,Object...args){
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //1.获取数据库连接
            conn = JDBCUtils.getConnection();
            //2.预编译sql语句，返回PrepareStatement的实例,预编译已经确定了sql格式，后续填入是数据
            ps = conn.prepareStatement(sql);
            //3.填充占位符
            for(int j = 0;j<args.length;++j){
                ps.setObject(j+1,args[j]);
            }
            //4.执行
            //如果是查询操作有返回结果，此方法返回true；增删改操作没有结果集返回false
            //ps.execute();
            //此方法会返回增删改影响行数
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //5.资源的关闭
            JDBCUtils.closeResource(conn,ps);
        }
        return 0;//执行失败返回0
    }

    /**
     * 4
     * 关闭资源的操作，查询时
     * @param conn
     * @param ps
     * @param rs
     */
    public static void closeResource(Connection conn, Statement ps,ResultSet rs){
        try {
            if(ps!=null)
                ps.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if(conn!=null)
                conn.close();
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }try{
        if(rs!=null)
            rs.close();
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }
    }


    /**
     * 5
     * 实现针对于不同表的通用查询操作，返回表中的多条数据
     * @param clazz  表的运行时类
     * @param sql
     * @param args
     * @param <T>   泛型
     * @return
     */
    public static <T> ArrayList<T> getForList(Class<T> clazz, String sql, Object...args){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            conn = JDBCUtils.getConnection();
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
            JDBCUtils.closeResource(conn, ps, resultSet);
        }
        return null;
    }

    /**
     * 6
     * @author oscarzqf
     * @description
     *   实现针对于不同表的通用查询操作，返回表中的一条数据
     * @create 2021-09-22-9:16
     */
    public static <T>  T getSingleDate(Class<T> clazz,String sql, Object...args){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            conn = JDBCUtils.getConnection();
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
                    declaredField.set(t, columnValue);//方法的参数本就为object类型
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
