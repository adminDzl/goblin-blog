package xyz.guqing.goblin.service;

import xyz.guqing.goblin.common.PageResult;
import xyz.guqing.goblin.entity.Microblog;

public interface MicroBlogService {
    void save(Microblog microblog);
    
    PageResult queryAll(Integer currentPage, Integer pageSize);
}
