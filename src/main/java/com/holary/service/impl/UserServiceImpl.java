package com.holary.service.impl;

import com.holary.mapper.UserMapper;
import com.holary.pojo.User;
import com.holary.service.UserService;
import com.holary.utils.Md5Util;
import com.holary.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

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

    /**
     * description: 修改用户基本信息
     *
     * @param user:
     * @return: void
     */
    @Override
    public void updateBasicInfo(User user) {
        userMapper.updateUserBasicInfo(user);
    }

    /**
     * description: 修改用户头像
     *
     * @param avatarUrl: 头像url地址
     * @return: void
     */
    @Override
    public void updateAvatar(String avatarUrl) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updateUserAvatar(id, avatarUrl);
    }

    /**
     * description: 用户修改密码
     *
     * @param password: 用户新密码
     * @return: void
     */
    @Override
    public void updatePassword(String password) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updateUserPassword(id, password);
    }
}
