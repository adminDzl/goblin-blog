package xyz.guqing.goblin.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import xyz.guqing.goblin.entity.Microblog;
import xyz.guqing.goblin.entity.MicroblogExample;

public interface MicroblogMapper {
    int countByExample(MicroblogExample example);

    int deleteByExample(MicroblogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Microblog record);

    int insertSelective(Microblog record);

    List<Microblog> selectByExample(MicroblogExample example);

    Microblog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Microblog record, @Param("example") MicroblogExample example);

    int updateByExample(@Param("record") Microblog record, @Param("example") MicroblogExample example);

    int updateByPrimaryKeySelective(Microblog record);

    int updateByPrimaryKey(Microblog record);
}