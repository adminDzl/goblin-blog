package xyz.guqing.goblin.service;

import xyz.guqing.goblin.common.PageResult;
import xyz.guqing.goblin.entity.Music;

public interface MusicService {
    void save(Music music) throws Exception;
    
    PageResult findAllByPage(Integer currentPage, Integer pageSize) throws Exception;
    
    void delete(Integer id) throws Exception;
    
    void update(Music music) throws Exception;
    
    Music getById(Integer id) throws Exception;
}
