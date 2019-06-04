package xyz.guqing.goblin.mapper;

import xyz.guqing.goblin.entity.BlogArticle;

public interface CustomBlogArticleMapper {
    int insert(BlogArticle record);
    
    int insertSelective(BlogArticle record);
}
