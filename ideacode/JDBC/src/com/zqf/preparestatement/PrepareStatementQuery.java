package com.zqf.preparestatement;

import com.zqf.bean.Customer;
import com.zqf.util.JDBCUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.*;

/**
 * @author oscarzqf
 * @description
 *          查询操作针对customers表
 * @create 2021-09-19-19:41
 */
public class PrepareStatementQuery {
    @Test
    public void testCustomersForQuery(){
        String sql="select id,name,birth,email from customers where id=?";
        Customer customer = customersForQuery(sql, 10);
        System.out.println(customer);
    }
    public Customer customersForQuery(String sql,Object...args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);
            for(int j = 0;j<args.length;++j){
                ps.setObject(j+1,args[j]);
            }
            resultSet = ps.executeQuery();
            //获取结果集的元数据：ResultSetMetaData
            ResultSetMetaData rsmd = resultSet.getMetaData();
            //通过ResultSetMetaData获取结果集的列数
            int columnCount=rsmd.getColumnCount();

            if(resultSet.next()){
                Customer cust=new Customer();
                //处理一行数据中的每一个字段
                for (int i = 0; i <columnCount ; i++) {
                    //获取列值
                    Object columnValue= resultSet.getObject(i + 1);
                    //获取每个列的列名
                    //String columnName = rsmd.getColumnName(i+1);--不推荐使用
                    //获取列的别名或列名
                    String columnLabel = rsmd.getColumnLabel(i + 1);
                    //给cust对象指定的columnLabel属性，赋值为colmnValue,通过反射
                    Field field = Customer.class.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(cust,columnValue);
                }
                return cust;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn,ps,resultSet);
        }
        return null;
    }

    @Test
    public void testQuery()  {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql="select id,name,email,birth from customers where id=?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1,1);
            //执行并返回结果集
            resultSet = ps.executeQuery();
            //处理结果集
            if(resultSet.next()){//next()判断结果集下一条是否有数据
                //如果true指针下移,如果false指针不会下移
                //获取当前数据的字段值
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String email = resultSet.getString(3);
                Date date = resultSet.getDate(4);//sql中的date
                /*//方式1
                System.out.println("id="+id+"name="+name+"email="+email
                +"date="+date);
                //方式2
                Object[] data=new Object[]{id,name,email,date};
                */
                //方式3:将数据封装进对象
                Customer customer=new Customer(id,name,email,date);
                System.out.println(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            JDBCUtils.closeResource(conn,ps,resultSet);
        }

    }
}
