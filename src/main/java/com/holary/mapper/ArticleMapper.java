package com.holary.mapper;

import com.github.pagehelper.Page;
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

    /**
     * description: 分页查询文章
     *
     * @param userId:     用户id
     * @param categoryId: 文章分类id
     * @param state:      文章状态
     * @return: com.github.pagehelper.Page<com.holary.pojo.Article>
     */
    Page<Article> selectArticleByPage(Integer userId, Integer categoryId, String state);

    /**
     * description: 根据文章id查询文章详情
     *
     * @param id: 文章id
     * @return: com.holary.pojo.Article
     */
    Article selectArticleDetailById(Integer id);

    /**
     * description: 根据文章id修改文章
     *
     * @param article: 文章对象
     * @return: void
     */
    void updateArticle(Article article);

    /**
     * description: 根据文章id删除文章
     *
     * @param id: 文章id
     * @return: void
     */
    void deleteArticleById(Integer id);
}
