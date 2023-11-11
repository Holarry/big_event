package com.holary.service;

import com.holary.pojo.Article;
import com.holary.pojo.PageBean;

/**
 * @Author: Holary
 * @Date: 2023/11/11 14:34
 * @Description: ArticleService
 */
public interface ArticleService {
    /**
     * description: 发布文章
     *
     * @param article: 文章对象
     * @return: void
     */
    void post(Article article);

    /**
     * description: 分页查询文章
     *
     * @param pageNum:    页码
     * @param pageSize:   条数
     * @param categoryId: 文章分类id
     * @param state:      文章状态
     * @return: com.holary.pojo.Result<com.holary.pojo.PageBean < com.holary.pojo.Article>>
     */
    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);
}
