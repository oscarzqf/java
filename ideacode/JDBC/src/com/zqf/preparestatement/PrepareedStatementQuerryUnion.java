package com.zqf.preparestatement;

import com.zqf.bean.Customer;
import com.zqf.bean.Order;
import com.zqf.util.JDBCUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * @author oscarzqf
 * @description
 *   实现针对于不同表的通用查询操作，返回表中的一条数据
 * @create 2021-09-22-9:16
 */
public class PrepareedStatementQuerryUnion {
    @Test
    public void testTestNormalQuery(){
        String sql="select id,name,email from customers where id<=?";
        ArrayList<Customer> customers = getForList(Customer.class, sql, 4);
        customers.forEach(System.out::println);

    }
    //使用反射传入运行时类,动态创建存数据的对象
    public <T> T testNormalQuery(Class<T> clazz,String sql, Object...args){
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
            JDBCUtils.closeResource(conn, ps, resultSet);
        }
        return null;
    }

    /**
     * 实现针对于不同表的通用查询操作，返回表中的多条数据
     * @param clazz
     * @param sql
     * @param args
     * @param <T>
     * @return
     */
    public <T> ArrayList<T> getForList(Class<T> clazz, String sql, Object...args){
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


}