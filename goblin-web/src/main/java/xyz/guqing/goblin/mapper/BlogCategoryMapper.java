package xyz.guqing.goblin.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import xyz.guqing.goblin.entity.BlogCategory;
import xyz.guqing.goblin.entity.BlogCategoryExample;

public interface BlogCategoryMapper {
    int countByExample(BlogCategoryExample example);

    int deleteByExample(BlogCategoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BlogCategory record);

    int insertSelective(BlogCategory record);

    List<BlogCategory> selectByExample(BlogCategoryExample example);

    BlogCategory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BlogCategory record, @Param("example") BlogCategoryExample example);

    int updateByExample(@Param("record") BlogCategory record, @Param("example") BlogCategoryExample example);

    int updateByPrimaryKeySelective(BlogCategory record);

    int updateByPrimaryKey(BlogCategory record);
}