package xyz.guqing.goblin.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import xyz.guqing.goblin.entity.BlogArticle;
import xyz.guqing.goblin.entity.BlogArticleExample;

public interface BlogArticleMapper {
    int countByExample(BlogArticleExample example);

    int deleteByExample(BlogArticleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BlogArticle record);

    int insertSelective(BlogArticle record);

    List<BlogArticle> selectByExample(BlogArticleExample example);

    BlogArticle selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BlogArticle record, @Param("example") BlogArticleExample example);

    int updateByExample(@Param("record") BlogArticle record, @Param("example") BlogArticleExample example);

    int updateByPrimaryKeySelective(BlogArticle record);

    int updateByPrimaryKey(BlogArticle record);
}