package com.zqf.preparestatement;

import com.zqf.bean.Order;
import com.zqf.util.JDBCUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.*;

/**
 * @author oscarzqf
 * @description   针对与order表的通用查询操作
 * @create 2021-09-19-21:05
 */
public class OrderForQuery {
    /*针对表的字段名与类的属性名不同的情况：
    *1.必须声明sql时，使用类的属性名来命名字段的别名
    * 2.使用ResultSetMetaData时，需要使用getColumnLabel(i + 1)
    * 来代替getColumnName()来获取列的别名
    * 3.如果sql中没有起别名，getColumnLabel()会获取列名
     */
    public void testedOrderForQuery(){
        //列的别名需要使用对应类中的属性名
        String sql="select order_id orderId,order_name orderName," +
                "order_date orderDate from `order` where order_id=?";
        Order order = orderForQuery(sql, 1);
        System.out.println(order);
    }
    public Order orderForQuery(String sql,Object...args)  {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }
            resultSet = ps.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            if(resultSet.next()){
                Order order = new Order();
                for(int i=0;i<columnCount;++i){
                    Object columnValue = resultSet.getObject(i + 1);
                    //获取列的列名
                    //String columnName = metaData.getColumnName(i + 1)--不推荐使用
                    //获取列的别名:再根据别名获取属性，使用反射
                    String columnLabel = metaData.getColumnLabel(i + 1);
                    Field declaredField = Order.class.getDeclaredField(columnLabel);
                    declaredField.setAccessible(true);
                    declaredField.set(order,columnValue);//方法的参数本就为object类型
                }
                return order;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn,ps,resultSet);
        }

        return null;
    }
    @Test
    public void testOrderForQuery()  {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            conn = JDBCUtils.getConnection();
            //注意表名和关键词一样的话会报错，需要使用``
            String sql="select order_id,order_name,order_date from `order` where order_id=?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1,1);
            resultSet = ps.executeQuery();
            if(resultSet.next()){
                int orderId = resultSet.getInt(1);
                String orderName = resultSet.getString(2);
                Date orderDate = resultSet.getDate(3);
                Order order = new Order(orderId, orderName, orderDate);
                System.out.println(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn,ps,resultSet);
        }


    }
}
