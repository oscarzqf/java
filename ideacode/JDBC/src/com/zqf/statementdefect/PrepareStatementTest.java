package com.zqf.statementdefect;

import com.zqf.util.JDBCUtils;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

/**
 * @author oscarzqf
 * @description    演示使用prepareStatement替换statement
 *                  来解决sql注入问题
 * @create 2021-09-22-12:09
 */
public class PrepareStatementTest {
    public static <T> T testNormalQuery(Class<T> clazz,String sql, Object...args){
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

}
