package com.holary.service.impl;

import com.holary.mapper.ArticleMapper;
import com.holary.pojo.Article;
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
}
