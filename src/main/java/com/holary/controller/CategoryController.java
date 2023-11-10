package com.holary.controller;

import com.holary.pojo.Category;
import com.holary.pojo.Result;
import com.holary.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PostMapping
    public Result save(@RequestBody @Validated Category category) {
        categoryService.save(category);
        return Result.success();
    }

    /**
     * description: 查询文章分类
     *
     * @return: com.holary.pojo.Result<java.util.List < com.holary.pojo.Category>>
     */
    @GetMapping
    public Result<List<Category>> list() {
        List<Category> list = categoryService.list();
        return Result.success(list);
    }

    /**
     * description: 根据分类id查询分类详情
     *
     * @param id: 分类id
     * @return: com.holary.pojo.Result<com.holary.pojo.Category>
     */
    @GetMapping("/detail")
    public Result<Category> getDetail(Integer id) {
        Category category = categoryService.getDetail(id);
        return Result.success(category);
    }

    /**
     * description: 根据分类id修改分类信息
     *
     * @param category: 分类对象
     * @return: com.holary.pojo.Result
     */
    @PutMapping
    public Result updateDetail(@RequestBody @Validated Category category) {
        categoryService.updateDetail(category);
        return Result.success();
    }
}
