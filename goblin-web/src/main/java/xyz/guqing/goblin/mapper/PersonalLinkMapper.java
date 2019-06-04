package xyz.guqing.goblin.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import xyz.guqing.goblin.entity.PersonalLink;
import xyz.guqing.goblin.entity.PersonalLinkExample;

public interface PersonalLinkMapper {
    int countByExample(PersonalLinkExample example);

    int deleteByExample(PersonalLinkExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PersonalLink record);

    int insertSelective(PersonalLink record);

    List<PersonalLink> selectByExample(PersonalLinkExample example);

    PersonalLink selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PersonalLink record, @Param("example") PersonalLinkExample example);

    int updateByExample(@Param("record") PersonalLink record, @Param("example") PersonalLinkExample example);

    int updateByPrimaryKeySelective(PersonalLink record);

    int updateByPrimaryKey(PersonalLink record);
}