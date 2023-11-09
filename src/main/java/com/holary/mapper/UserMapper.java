package com.holary.mapper;

import com.holary.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: Holary
 * @Date: 2023/11/9 17:08
 * @Description: UserMapper
 */
@Mapper
public interface UserMapper {
    /**
     * description: 根据用户名查询用户, 用于判断用户是否存在
     *
     * @param username: 用户名
     * @return: com.holary.pojo.User
     */
    User findByUsername(String username);

    /**
     * description: 注册用户
     *
     * @param username:    用户名
     * @param md5Password: 密码
     * @return: void
     */
    void insertUser(String username, String md5Password);

    /**
     * description: 修改用户基本信息
     *
     * @param user:
     * @return: void
     */
    void updateUserBasicInfo(User user);
}
