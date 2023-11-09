package com.holary.controller;

import com.holary.pojo.Result;
import com.holary.pojo.User;
import com.holary.service.UserService;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Holary
 * @Date: 2023/11/9 17:05
 * @Description: UserController
 */
@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * description: 用户注册
     *
     * @param username: 用户名
     * @param password: 密码
     * @return: com.holary.pojo.Result
     */
    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        // 检查询用户是否存在
        User user = userService.findByUsername(username);
        if (user != null) {
            return Result.error(username + "已存在!");
        }
        // 注册
        userService.register(username, password);
        return Result.success();
    }
}
