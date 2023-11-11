package com.holary.validation;

import com.holary.annotation.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * @Author: Holary
 * @Date: 2023/11/11 14:29
 * @Description: StateValidation
 */
public class StateValidation implements ConstraintValidator<State, String> {
    /**
     * description:
     *
     * @param s:                          要校验的数据
     * @param constraintValidatorContext:
     * @return: boolean
     */
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        // 校验规则
        if (s == null) {
            return false;
        }

        return s.equals("已发布") || s.equals("草稿");
    }
}
