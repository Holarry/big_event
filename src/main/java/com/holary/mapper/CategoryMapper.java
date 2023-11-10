package com.holary.mapper;

import com.holary.pojo.Category;
import org.apache.ibatis.annotations.Mapper;

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
}
