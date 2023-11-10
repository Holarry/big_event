package com.holary.controller;

import com.holary.pojo.Category;
import com.holary.pojo.Result;
import com.holary.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Holary
 * @Date: 2023/11/9 17:57
 * @Description: CategoryController
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * description: 新增分类
     *
     * @param category: 分类对象
     * @return: com.holary.pojo.Result
     */
    @PostMapping()
    public Result save(@RequestBody @Validated Category category) {
        categoryService.save(category);
        return Result.success();
    }

}
