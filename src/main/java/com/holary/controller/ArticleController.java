package com.holary.controller;

import com.holary.pojo.Article;
import com.holary.pojo.PageBean;
import com.holary.pojo.Result;
import com.holary.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    /**
     * description: 分页查询文章
     *
     * @param pageNum:    页码
     * @param pageSize:   条数
     * @param categoryId: 文章分类id
     * @param state:      文章状态
     * @return: com.holary.pojo.Result<com.holary.pojo.PageBean < com.holary.pojo.Article>>
     */
    @GetMapping
    public Result<PageBean<Article>> list(Integer pageNum, Integer pageSize,
                                          @RequestParam(required = false) Integer categoryId,
                                          @RequestParam(required = false) String state) {
        PageBean<Article> pageBean = articleService.list(pageNum, pageSize, categoryId, state);
        return Result.success(pageBean);
    }
}
