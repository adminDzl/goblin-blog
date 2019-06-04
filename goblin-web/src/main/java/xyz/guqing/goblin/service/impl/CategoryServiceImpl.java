package xyz.guqing.goblin.service.impl;

import org.springframework.stereotype.Service;
import xyz.guqing.goblin.common.CustomException;
import xyz.guqing.goblin.entity.BlogCategory;
import xyz.guqing.goblin.entity.BlogCategoryExample;
import xyz.guqing.goblin.mapper.BlogCategoryMapper;
import xyz.guqing.goblin.mapper.CustomCategoryMapper;
import xyz.guqing.goblin.service.CategoryService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private BlogCategoryMapper blogCategoryMapper;
    @Resource
    private CustomCategoryMapper customCategoryMapper;
    
    @Override
    public List<BlogCategory> findAll() throws Exception{
       
        return customCategoryMapper.findAll();
    }
    
    @Override
    public void update(BlogCategory category) throws CustomException {
        if(category.getId() == null){
            throw new CustomException("必须的分类id，不存在");
        }
        // 更新数据
        blogCategoryMapper.updateByPrimaryKeySelective(category);
    }
    
    @Override
    public void save(BlogCategory category) throws CustomException {
        // 1.先保存
        category.setArticleCount(0);
        category.setCreateTime(new Date());
        category.setModifyTime(category.getCreateTime());
        customCategoryMapper.insertSelective(category);
      
        // 2.获取到id,在更新一下排序字段
        BlogCategory categoryModel = new BlogCategory();
        categoryModel.setId(category.getId());
        categoryModel.setSortKey(category.getId());
        this.update(categoryModel);
    }
    
    
    @Override
    public void delete(Integer id) throws Exception{
        blogCategoryMapper.deleteByPrimaryKey(id);
    }
    
    @Override
    public BlogCategory findOneAbove(int id) {
        return customCategoryMapper.findOneAbove(id);
    }
    
    @Override
    public BlogCategory findOneBelow(int id) {
        return customCategoryMapper.findOneBelow(id);
    }
    
    @Override
    public void moveUp(int id) throws CustomException {
        //找出上面的Category
        BlogCategory blogCategory = blogCategoryMapper.selectByPrimaryKey(id);
        BlogCategory other = this.findOneAbove(blogCategory.getSortKey());
        //最上面的不能上移
        exchangeRecod(blogCategory, other);
    }
    
    @Override
    public void moveDown(int id) throws CustomException {
        BlogCategory blogCategory = blogCategoryMapper.selectByPrimaryKey(id);
        //找出上面的Category
        BlogCategory other = this.findOneBelow(blogCategory.getSortKey());
        //最下面的不能下移
        exchangeRecod(blogCategory,other);
    }
    
    private void exchangeRecod(BlogCategory blogCategory,BlogCategory other) throws CustomException {
       
        if (other == null) {
            return;
        }
      
        //交换排序字段sortKey
        int temp = blogCategory.getSortKey();
        blogCategory.setSortKey(other.getSortKey());
        other.setSortKey(temp);
        
        //更新到数据库中
        this.update(blogCategory);
        this.update(other);
    }
    
}
