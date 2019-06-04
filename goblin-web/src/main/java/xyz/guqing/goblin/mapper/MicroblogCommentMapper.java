package xyz.guqing.goblin.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import xyz.guqing.goblin.entity.MicroblogComment;
import xyz.guqing.goblin.entity.MicroblogCommentExample;

public interface MicroblogCommentMapper {
    int countByExample(MicroblogCommentExample example);

    int deleteByExample(MicroblogCommentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MicroblogComment record);

    int insertSelective(MicroblogComment record);

    List<MicroblogComment> selectByExample(MicroblogCommentExample example);

    MicroblogComment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MicroblogComment record, @Param("example") MicroblogCommentExample example);

    int updateByExample(@Param("record") MicroblogComment record, @Param("example") MicroblogCommentExample example);

    int updateByPrimaryKeySelective(MicroblogComment record);

    int updateByPrimaryKey(MicroblogComment record);
}