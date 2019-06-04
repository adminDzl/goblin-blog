package xyz.guqing.goblin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xyz.guqing.goblin.common.CustomException;
import xyz.guqing.goblin.common.PageResult;
import xyz.guqing.goblin.entity.BlogArticle;
import xyz.guqing.goblin.entity.Music;
import xyz.guqing.goblin.mapper.MusicMapper;
import xyz.guqing.goblin.service.MusicService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class MusicServiceImpl implements MusicService {
    @Resource
    private MusicMapper musicMapper;
    
    @Override
    @Transactional(isolation= Isolation.DEFAULT,propagation= Propagation.REQUIRED,rollbackFor= CustomException.class)
    public void save(Music music) throws Exception{
        music.setListenCount(0);
        music.setCreateTime(new Date());
        music.setModifyTime(music.getCreateTime());
        musicMapper.insertSelective(music);
    }
    
    @Override
    public PageResult findAllByPage(Integer currentPage, Integer pageSize) throws Exception {
        PageHelper.startPage(currentPage, pageSize);
        
        List<Music> musicList = musicMapper.selectByExample(null);
        
        PageInfo<Music> pageInfo = new PageInfo<Music>(musicList);
        
        return new PageResult(pageInfo.getTotal(),pageInfo.getList());
    }
    
    @Override
    public void delete(Integer id) throws Exception {
        musicMapper.deleteByPrimaryKey(id);
    }
    
    @Override
    public void update(Music music) throws Exception {
        if(music.getId() == null){
            return;
        }
        musicMapper.updateByPrimaryKeySelective(music);
    }
    
    @Override
    public Music getById(Integer id) throws Exception {
        return musicMapper.selectByPrimaryKey(id);
    }
}
