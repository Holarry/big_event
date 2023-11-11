package com.holary.controller;

import com.holary.pojo.Result;
import com.holary.pojo.User;
import com.holary.service.UserService;
import com.holary.utils.JwtUtil;
import com.holary.utils.Md5Util;
import com.holary.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

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

        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        valueOperations.set(token, token, 1, TimeUnit.HOURS);

        return Result.success(token);
    }

    /**
     * description: 查询用户详细信息
     *
     * @return: com.holary.pojo.Result<com.holary.pojo.User>
     */
    @GetMapping("/getDetailInfo")
    public Result<User> getDetailInfo() {
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
    @PutMapping("/updateBasicInfo")
    public Result updateBasicInfo(@RequestBody @Validated User user) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        if (!id.equals(user.getId())) {
            return Result.error("用户id不匹配!");
        }
        userService.updateBasicInfo(user);
        return Result.success();
    }

    /**
     * description: 修改用户头像
     *
     * @param avatarUrl: 头像url地址
     * @return: com.holary.pojo.Result
     */
    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl) {
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    /**
     * description: 用户修改密码
     *
     * @param params:
     * @return: com.holary.pojo.Result
     */
    @PatchMapping("/updatePassword")
    public Result updatePassword(@RequestBody Map<String, String> params, @RequestHeader("Authorization") String token) {
        String oldPwd = params.get("oldPwd");
        String newPwd = params.get("newPwd");
        String rePwd = params.get("rePwd");

        // 判断密码是否输入完整
        if (!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd)) {
            return Result.error("密码未填写完整!");
        }

        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.findByUsername(username);
        // 判断原密码是否正确
        if (!user.getPassword().equals(Md5Util.getMD5String(oldPwd))) {
            return Result.error("原密码错误!");
        }

        // 判断两次新密码是否一致
        if (!newPwd.equals(rePwd)) {
            return Result.error("新密码输入不一致!");
        }

        // 修改密码
        userService.updatePassword(Md5Util.getMD5String(newPwd));
        // 删除redis中对应的token
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        valueOperations.getOperations().delete(token);

        return Result.success();
    }
}
