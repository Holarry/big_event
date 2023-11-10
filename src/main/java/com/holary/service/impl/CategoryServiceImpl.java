package com.holary.service.impl;

import com.holary.mapper.CategoryMapper;
import com.holary.pojo.Category;
import com.holary.service.CategoryService;
import com.holary.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @Author: Holary
 * @Date: 2023/11/9 17:57
 * @Description: CategoryServiceImpl
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * description: 新增分类
     *
     * @param category: 分类对象
     * @return: void
     */
    @Override
    public void save(Category category) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        category.setCreateUser(id);
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.insertCategory(category);
    }
}
