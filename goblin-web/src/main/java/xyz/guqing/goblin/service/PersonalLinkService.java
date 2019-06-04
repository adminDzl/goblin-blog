package xyz.guqing.goblin.service;


import xyz.guqing.goblin.common.PageResult;
import xyz.guqing.goblin.entity.PersonalLink;

public interface PersonalLinkService {
    void save(PersonalLink personalLink) throws Exception;
    
    PersonalLink getById(Integer id) throws Exception;
    
    PageResult queryAll(Integer currentPage, Integer pageSize) throws Exception;
    
    void update(PersonalLink personalLink) throws Exception;
    
    void delete(Integer id) throws Exception;
}
