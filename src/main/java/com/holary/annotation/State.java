package com.holary.annotation;

import com.holary.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * @Author: Holary
 * @Date: 2023/11/11 14:27
 * @Description: 自定义注解
 */
@Documented // 元注解
@Target({ElementType.FIELD}) // 元注解
@Retention(RetentionPolicy.RUNTIME) // 元注解
@Constraint(validatedBy = {StateValidation.class}) // 指定提供校验规则的类
public @interface State {
    // 校验失败后的提示信息
    String message() default "该属性只能为'已发布'或'草稿'!";

    // 指定分组
    Class<?>[] groups() default {};

    // 负载
    Class<? extends Payload>[] payload() default {};
}
