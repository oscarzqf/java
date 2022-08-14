package com.zqf.service.impl;

import com.zqf.dao.UserDao;
import com.zqf.dao.impl.UserDaoIml;
import com.zqf.pojo.User;
import com.zqf.service.UserService;
import com.zqf.utils.JDBCUtils;

import java.sql.Connection;

/**
 * @author oscarzqf
 * @description
 * @create 2021-11-29-12:29
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao=new UserDaoIml();
    @Override
    public void registerUser(User user) {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            userDao.saveUser(connection,user);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection);
        }
    }

    @Override
    public User login(User user) {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            return userDao.queryUserByUsernameAndPassword(connection,user.getUsername(),user.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection);
        }
        return null;
    }

    @Override
    public boolean existUsername(String username) {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            if (userDao.queryUserByUsername(connection,username)==null)
                return false;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection);
        }
        return true;
    }
}
