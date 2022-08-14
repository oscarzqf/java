package com.zqf.service;

import com.zqf.pojo.User;

/**
 * @author oscarzqf
 * @description
 * @create 2021-11-29-12:22
 */
public interface UserService {
    /**
     * 注册用户
     * @param user
     */
    public void registerUser(User user);
    /**
     * 登录
     * 返回null登录失败,有对象则登录成功
     * @param user
     */
    public User login(User user);

    /**
     * 检查用户名是否可以使用
     * @param username
     * @return 返回true表示用户名已经存在
     * 返回false表示用户名可以使用
     */
    public boolean existUsername(String username);


}
