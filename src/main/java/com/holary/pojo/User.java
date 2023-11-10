package com.holary.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author: Holary
 * @Date: 2023/11/9 16:50
 * @Description: 用户实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @NotNull
    private Integer id;//主键ID
    private String username;//用户名
    @JsonIgnore //将当前对象转成JSON字符串时忽略password
    private String password;//密码
    @NotNull
    @Pattern(regexp = "^\\S{1,10}$")
    private String nickname;//昵称
    @NotNull
    @Email
    private String email;//邮箱
    private String userPic;//用户头像地址
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;//创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;//更新时间
}
