package xyz.guqing.goblin.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import xyz.guqing.goblin.entity.AlbumFolder;
import xyz.guqing.goblin.entity.AlbumFolderExample;

public interface AlbumFolderMapper {
    int countByExample(AlbumFolderExample example);

    int deleteByExample(AlbumFolderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AlbumFolder record);

    int insertSelective(AlbumFolder record);

    List<AlbumFolder> selectByExample(AlbumFolderExample example);

    AlbumFolder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AlbumFolder record, @Param("example") AlbumFolderExample example);

    int updateByExample(@Param("record") AlbumFolder record, @Param("example") AlbumFolderExample example);

    int updateByPrimaryKeySelective(AlbumFolder record);

    int updateByPrimaryKey(AlbumFolder record);
}