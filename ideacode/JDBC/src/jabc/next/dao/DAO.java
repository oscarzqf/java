package jabc.next.dao;

import com.zqf.util.JDBCUtils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author oscarzqf
 * @description
 *封装了针对于数据表的通用的操作
 * DAO:  database access object
 * @create 2021-09-27-22:20
 */
public abstract class DAO {
    /**通用的增删改2.0----考虑事务
     * 思想就是一个连接做多个sql操作
     * 于是将一个连接作为参数串联多个操作
     * 出现异常就回滚
     * */
    public int update(Connection conn, String sql, Object...args){
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
     * @author oscarzqf
     * @description
     *   getSingleDate 2.0
     *   实现针对于不同表的通用查询操作，返回表中的一条数据--考虑事务
     * @create 2021-09-22-9:16
     */
    public  <T>  T getSingleDate(Connection conn,Class<T> clazz,String sql, Object...args){
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

    /**
     * 通用查询getForList2.0
     * 实现针对于不同表的通用查询操作，返回表中的多条数据构成的集合----考虑事务
     * @param clazz  表的运行时类
     * @param <T>   泛型
     */
    public  <T> List<T> getForList(Connection conn, Class<T> clazz, String sql, Object...args){
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

    /**
     * 用于查询特殊值的通用的方法
     * */
    public<E> E getValue(Connection conn,String sql,Object...args){
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }
            rs = ps.executeQuery();
            if(rs.next()){
                return (E)rs.getObject(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtils.closeResource(null,ps,rs);
        }
        return null;
    }
}
