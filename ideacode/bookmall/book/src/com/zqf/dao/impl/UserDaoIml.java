package com.zqf.dao.impl;

import com.zqf.dao.DAO;
import com.zqf.dao.UserDao;
import com.zqf.pojo.User;

import java.sql.Connection;

/**
 * @author oscarzqf
 * @description
 * @create 2021-11-29-9:21
 */
public class UserDaoIml extends DAO<User> implements UserDao {
    @Override
    public User queryUserByUsername(Connection connection, String username) {
        String sql="select `id`,`username`,`password`,`email` from t_user where username=?";
        User singleDate = getSingleDate(connection, sql, username);
        return singleDate;
    }

    @Override
    public User queryUserByUsernameAndPassword(Connection connection, String username, String password) {
        String sql="select `id`,`username`,`password`,`email` from t_user where username=? and password= ?";
        User massageDate = getSingleDate(connection, sql, username,password);
        return massageDate;
    }

    @Override
    public int saveUser(Connection connection, User user) {
        String sql="insert into t_user(`username`,`password`,`email`) values(?,?,?)";
        return update(connection,sql,user.getUsername(),user.getPassword(),user.getEmail());
    }
}
