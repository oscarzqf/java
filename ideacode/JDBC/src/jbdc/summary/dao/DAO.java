package jbdc.summary.dao;

import com.zqf.util.JDBCUtils;
import jbdc.summary.achieve.JDBCUtilsFinal;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author oscarzqf
 * @description
 *封装了针对于数据表的通用的操作,抽象类不能实例化
 * DAO:  database access object
 * @create 2021-09-27-22:20
 */
public abstract class DAO<T> {
    private Class<T> clazz=null;
    //赋值代码块，此处较难理解，（将this当做调用方法的对象，如果直接创建父类对象，则父类中的this
    // 就是指父类对象本身；如果创建子类对象，通过子类对象调用方法中的this为子类对象）
    {
        //获取当前DAO的子类继承的父类中的泛型,此时的this为DAO的继承类对象
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        ParameterizedType paramType=(ParameterizedType) genericSuperclass;
        //获取运行时类带泛型的父类的泛型
        Type[] actualTypeArguments = paramType.getActualTypeArguments();
        clazz=(Class<T>)actualTypeArguments[0];
    }
    /**
     * 通用的增删改2.0
     */
    public int update(Connection conn, String sql, Object...args){
        try {
            QueryRunner runner = new QueryRunner();
            return runner.update(conn,sql,args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //修改失败返回0
        return 0;
    }
    /**
     * @author oscarzqf
     * @description
     *   getSingleDate 2.0
     *   实现针对于不同表的通用查询操作，返回表中的一条数据
     * @create 2021-09-22-9:16
     */
    public T getSingleDate(Connection conn,String sql, Object...args){
        try {
            QueryRunner runner = new QueryRunner();
            BeanHandler<T> handler = new BeanHandler<T>(clazz);
            T query = runner.query(conn, sql, handler, args);
            return query;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    /**
     * 通用查询getForList2.0
     * 实现针对于不同表的通用查询操作，返回表中的多条数据构成的集合----考虑事务
     * @param clazz  表的运行时类
     * @param <T>   泛型
     */
    public  List<T> getForList(Connection conn, String sql, Object...args){
        try {
            QueryRunner runner = new QueryRunner();
            BeanListHandler<T> handler = new BeanListHandler<T>(clazz);
            List<T> list = runner.query(conn, sql, handler, args);
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    /**
     * 用于查询特殊值的通用的方法
     * */
    public<E> E getValue(Connection conn,String sql,Object...args){
        try {
            QueryRunner runner = new QueryRunner();
            ScalarHandler handler = new ScalarHandler();
           return (E) runner.query(conn, sql, handler, args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
       return null;
    }
}

