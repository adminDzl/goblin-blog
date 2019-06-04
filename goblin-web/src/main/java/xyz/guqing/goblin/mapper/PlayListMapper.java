package xyz.guqing.goblin.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import xyz.guqing.goblin.entity.PlayList;
import xyz.guqing.goblin.entity.PlayListExample;

public interface PlayListMapper {
    int countByExample(PlayListExample example);

    int deleteByExample(PlayListExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PlayList record);

    int insertSelective(PlayList record);

    List<PlayList> selectByExample(PlayListExample example);

    PlayList selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PlayList record, @Param("example") PlayListExample example);

    int updateByExample(@Param("record") PlayList record, @Param("example") PlayListExample example);

    int updateByPrimaryKeySelective(PlayList record);

    int updateByPrimaryKey(PlayList record);
}