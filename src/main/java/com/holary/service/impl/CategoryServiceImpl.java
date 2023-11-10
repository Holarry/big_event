package com.holary.service.impl;

import com.holary.mapper.CategoryMapper;
import com.holary.pojo.Category;
import com.holary.service.CategoryService;
import com.holary.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
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

    /**
     * description: 查询文章分类
     *
     * @return: java.util.List<com.holary.pojo.Category>
     */
    @Override
    public List<Category> list() {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        return categoryMapper.selectCategoryByUserId(id);
    }

    /**
     * description: 根据分类id查询分类详情
     *
     * @param id: 分类id
     * @return: com.holary.pojo.Category
     */
    @Override
    public Category getDetail(Integer id) {
        return categoryMapper.selectCategoryDetailById(id);
    }
}
