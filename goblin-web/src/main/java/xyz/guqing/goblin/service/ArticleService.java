package xyz.guqing.goblin.service;

import xyz.guqing.goblin.common.PageResult;
import xyz.guqing.goblin.entity.BlogArticle;
import xyz.guqing.goblin.entity.BlogArticleVO;

import java.util.List;

public interface ArticleService{
    
    void save(BlogArticleVO articleVO) throws Exception;
    
    PageResult findDetailByPage(Integer currentPage, Integer pageSize);
    
    PageResult findByPage(Integer currentPage, Integer pageSize);
    
    void update(BlogArticleVO blogArticleVO);
    
    BlogArticleVO getById(Integer id);
}
