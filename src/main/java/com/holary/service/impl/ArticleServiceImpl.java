package com.holary.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.holary.mapper.ArticleMapper;
import com.holary.pojo.Article;
import com.holary.pojo.PageBean;
import com.holary.service.ArticleService;
import com.holary.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author: Holary
 * @Date: 2023/11/11 14:34
 * @Description: ArticleServiceImpl
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    /**
     * description: 发布文章
     *
     * @param article: 文章对象
     * @return: void
     */
    @Override
    public void post(Article article) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        article.setCreateUser(id);

        articleMapper.insertArticle(article);
    }

    /**
     * description: 分页查询文章
     *
     * @param pageNum:    页码
     * @param pageSize:   条数
     * @param categoryId: 文章分类id
     * @param state:      文章状态
     * @return: com.holary.pojo.Result<com.holary.pojo.PageBean < com.holary.pojo.Article>>
     */
    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        PageBean<Article> pageBean = new PageBean<>();

        // 开启分页查询
        PageHelper.startPage(pageNum, pageSize);

        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        Page<Article> articleList = articleMapper.selectArticleByPage(userId, categoryId, state);

        pageBean.setTotal(articleList.getTotal());
        pageBean.setItems(articleList.getResult());
        return pageBean;
    }

    /**
     * description: 根据文章id查询文章详情
     *
     * @param id: 文章id
     * @return: com.holary.pojo.Article
     */
    @Override
    public Article getDetail(Integer id) {
        return articleMapper.selectArticleDetailById(id);
    }

    /**
     * description: 根据文章id修改文章
     *
     * @param article: 文章对象
     * @return: void
     */
    @Override
    public void update(Article article) {
        articleMapper.updateArticle(article);
    }
}
