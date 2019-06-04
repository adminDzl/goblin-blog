package xyz.guqing.goblin.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import xyz.guqing.goblin.entity.DiskFolder;
import xyz.guqing.goblin.entity.DiskFolderExample;

public interface DiskFolderMapper {
    int countByExample(DiskFolderExample example);

    int deleteByExample(DiskFolderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DiskFolder record);

    int insertSelective(DiskFolder record);

    List<DiskFolder> selectByExample(DiskFolderExample example);

    DiskFolder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DiskFolder record, @Param("example") DiskFolderExample example);

    int updateByExample(@Param("record") DiskFolder record, @Param("example") DiskFolderExample example);

    int updateByPrimaryKeySelective(DiskFolder record);

    int updateByPrimaryKey(DiskFolder record);
}