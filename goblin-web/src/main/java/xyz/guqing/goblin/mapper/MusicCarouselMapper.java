package xyz.guqing.goblin.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import xyz.guqing.goblin.entity.MusicCarousel;
import xyz.guqing.goblin.entity.MusicCarouselExample;

public interface MusicCarouselMapper {
    int countByExample(MusicCarouselExample example);

    int deleteByExample(MusicCarouselExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MusicCarousel record);

    int insertSelective(MusicCarousel record);

    List<MusicCarousel> selectByExample(MusicCarouselExample example);

    MusicCarousel selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MusicCarousel record, @Param("example") MusicCarouselExample example);

    int updateByExample(@Param("record") MusicCarousel record, @Param("example") MusicCarouselExample example);

    int updateByPrimaryKeySelective(MusicCarousel record);

    int updateByPrimaryKey(MusicCarousel record);
}