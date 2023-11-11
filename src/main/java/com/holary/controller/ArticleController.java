package com.holary.controller;

import com.holary.pojo.Article;
import com.holary.pojo.Result;
import com.holary.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Holary
 * @Date: 2023/11/11 14:31
 * @Description: ArticleController
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    /**
     * description: 发布文章
     *
     * @param article: 文章对象
     * @return: com.holary.pojo.Result
     */
    @PostMapping
    public Result post(@RequestBody @Validated Article article) {
        articleService.post(article);
        return Result.success();
    }
}
