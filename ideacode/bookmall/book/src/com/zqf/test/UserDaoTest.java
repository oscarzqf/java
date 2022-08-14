package com.zqf.test;

import com.zqf.dao.UserDao;
import com.zqf.dao.impl.UserDaoIml;
import com.zqf.pojo.User;
import com.zqf.utils.JDBCUtils;
import org.junit.Test;

import javax.xml.stream.events.Comment;
import java.sql.Connection;

import static org.junit.Assert.*;

/**
 * @author oscarzqf
 * @description
 * @create 2021-11-29-9:42
 */
public class UserDaoTest {

    @Test
    public void queryUserByUsername() {
        UserDao userDao=new UserDaoIml();
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            if(userDao.queryUserByUsername(connection,"admin")==null){
                System.out.println("用户名可用");
            }else{
                System.out.println("用户名已经存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection);
        }


    }

    @Test
    public void queryUserByUsernameAndPassword() {
        UserDao userDao=new UserDaoIml();
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            if(userDao.queryUserByUsernameAndPassword(connection,"admin","admin")==null){
                System.out.println("用户名或密码错误，登录失败");
            }else{
                System.out.println("登录成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection);
        }
    }

    @Test
    public void saveUser(){
        UserDao userDao=new UserDaoIml();
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            userDao.saveUser(connection,new User(null,"admin1","123456","228371071com"));

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection);
        }

    }
}