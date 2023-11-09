package com.holary.controller;

import com.holary.pojo.Result;
import com.holary.pojo.User;
import com.holary.service.UserService;
import com.holary.utils.JwtUtil;
import com.holary.utils.Md5Util;
import com.holary.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
        // 查询用户名是否已经存在
        User user = userService.findByUsername(username);
        if (user != null) {
            return Result.error(username + "已存在!");
        }
        // 注册
        userService.register(username, password);
        return Result.success();
    }

    /**
     * description: 用户登录
     *
     * @param username: 用户名
     * @param password: 密码
     * @return: com.holary.pojo.Result<java.lang.String>
     */
    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        // 查询用户是否存在
        User user = userService.findByUsername(username);
        if (user == null) {
            return Result.error(username + "不存在!");
        }

        // 判断用户名和密码是否匹配
        if (!user.getPassword().equals(Md5Util.getMD5String(password))) {
            return Result.error("用户名或密码错误!");
        }

        // 生成JWT令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("username", user.getUsername());
        String token = JwtUtil.genToken(claims);

        return Result.success(token);
    }

    /**
     * description: 查询用户详细信息
     *
     * @return: com.holary.pojo.Result<com.holary.pojo.User>
     */
    @GetMapping("/getUserInfo")
    public Result<User> getUserInfo() {
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.findByUsername(username);
        return Result.success(user);
    }

    /**
     * description: 修改用户基本信息
     *
     * @param user:
     * @return: com.holary.pojo.Result
     */
    @PutMapping("/updateUserBasicInfo")
    public Result updateUserBasicInfo(@RequestBody @Validated User user) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        if (!id.equals(user.getId())) {
            return Result.error("用户id不匹配!");
        }
        userService.updateUserBasicInfo(user);
        return Result.success();
    }
}
