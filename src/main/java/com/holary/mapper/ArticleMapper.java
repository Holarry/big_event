package com.holary.mapper;

import com.holary.pojo.Article;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: Holary
 * @Date: 2023/11/11 14:32
 * @Description: ArticleMapper
 */
@Mapper
public interface ArticleMapper {
    /**
     * description: 发布文章
     *
     * @param article: 文章对象
     * @return: void
     */
    void insertArticle(Article article);
}
