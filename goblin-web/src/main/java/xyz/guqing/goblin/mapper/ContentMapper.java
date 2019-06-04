package xyz.guqing.goblin.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import xyz.guqing.goblin.entity.Content;
import xyz.guqing.goblin.entity.ContentExample;

public interface ContentMapper {
    int countByExample(ContentExample example);

    int deleteByExample(ContentExample example);

    int deleteByPrimaryKey(Integer bid);

    int insert(Content record);

    int insertSelective(Content record);

    List<Content> selectByExampleWithBLOBs(ContentExample example);

    List<Content> selectByExample(ContentExample example);

    Content selectByPrimaryKey(Integer bid);

    int updateByExampleSelective(@Param("record") Content record, @Param("example") ContentExample example);

    int updateByExampleWithBLOBs(@Param("record") Content record, @Param("example") ContentExample example);

    int updateByExample(@Param("record") Content record, @Param("example") ContentExample example);

    int updateByPrimaryKeySelective(Content record);

    int updateByPrimaryKeyWithBLOBs(Content record);
}