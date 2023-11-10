package com.holary.service;

import com.holary.pojo.Category;

import java.util.List;

/**
 * @Author: Holary
 * @Date: 2023/11/9 17:57
 * @Description: CategoryService
 */
public interface CategoryService {
    /**
     * description: 新增分类
     *
     * @param category: 分类对象
     * @return: void
     */
    void save(Category category);

    /**
     * description: 查询文章分类
     *
     * @return: java.util.List<com.holary.pojo.Category>
     */
    List<Category> list();
}
