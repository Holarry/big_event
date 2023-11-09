package com.holary.service.impl;

import com.holary.mapper.UserMapper;
import com.holary.pojo.User;
import com.holary.service.UserService;
import com.holary.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Holary
 * @Date: 2023/11/9 17:08
 * @Description: UserServiceImpl
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * description: 根据用户名查询用户, 用于判断用户是否存在
     *
     * @param username: 用户名
     * @return: com.holary.pojo.User
     */
    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    /**
     * description: 注册用户
     *
     * @param username: 用户名
     * @param password: 密码
     * @return: void
     */
    @Override
    public void register(String username, String password) {
        // 使用MD5对用户密码加密
        String md5Password = Md5Util.getMD5String(password);
        // 添加用户
        userMapper.insertUser(username, md5Password);
    }
}
