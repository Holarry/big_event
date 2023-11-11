package com.holary.service;

import com.holary.pojo.Article;

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
}
