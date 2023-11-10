package com.holary.mapper;

import com.holary.pojo.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: Holary
 * @Date: 2023/11/9 17:58
 * @Description: CategoryMapper
 */
@Mapper
public interface CategoryMapper {
    /**
     * description: 新增分类
     *
     * @param category: 分类对象
     * @return: void
     */
    void insertCategory(Category category);

    /**
     * description: 根据用户id查询文章分类
     *
     * @param id: 当前用户id
     * @return: java.util.List<com.holary.pojo.Category>
     */
    List<Category> selectCategoryByUserId(Integer id);

    /**
     * description: 根据分类id查询分类详情
     *
     * @param id: 分类id
     * @return: com.holary.pojo.Category
     */
    Category selectCategoryDetailById(Integer id);

    /**
     * description: 根据分类id修改分类信息
     *
     * @param category: 分类对象
     * @return: void
     */
    void updateDetailById(Category category);
}
