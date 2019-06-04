package xyz.guqing.goblin.mapper;

import org.apache.ibatis.annotations.Param;
import xyz.guqing.goblin.entity.BlogCategory;
import xyz.guqing.goblin.entity.BlogCategoryExample;

import java.util.List;

public interface CustomCategoryMapper {

    int insert(BlogCategory record);

    int insertSelective(BlogCategory record);
    
    List<BlogCategory> findAll();
    
    BlogCategory findOneAbove(int id);
    
    BlogCategory findOneBelow(int id);
}