package xyz.guqing.goblin.service;

import xyz.guqing.goblin.common.CustomException;
import xyz.guqing.goblin.common.Result;
import xyz.guqing.goblin.entity.BlogCategory;

import java.util.List;

public interface CategoryService {
    
    List<BlogCategory> findAll() throws Exception;
    
    void update(BlogCategory category) throws CustomException;
    
    void save(BlogCategory category) throws CustomException;
    
    void delete(Integer id) throws Exception;
    
    /**
     * 寻找某个id上面的第一条记录
     * @return
     */
    BlogCategory findOneAbove(int id);
    
    /**
     * 寻找某个id下面的第一条记录
     * @return
     */
    BlogCategory findOneBelow(int id);
    
    void moveUp(int id) throws CustomException;
    
    void moveDown(int id) throws CustomException;
}
