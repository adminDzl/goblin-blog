package xyz.guqing.goblin.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xyz.guqing.goblin.common.CustomException;
import xyz.guqing.goblin.common.PageResult;
import xyz.guqing.goblin.entity.BlogArticle;
import xyz.guqing.goblin.entity.BlogArticleVO;
import xyz.guqing.goblin.entity.BlogCategory;
import xyz.guqing.goblin.entity.Content;
import xyz.guqing.goblin.mapper.BlogArticleMapper;
import xyz.guqing.goblin.mapper.BlogCategoryMapper;
import xyz.guqing.goblin.mapper.ContentMapper;
import xyz.guqing.goblin.mapper.CustomBlogArticleMapper;
import xyz.guqing.goblin.service.ArticleService;
import xyz.guqing.goblin.service.CategoryService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private BlogArticleMapper blogArticleMapper;
    @Resource
    private CustomBlogArticleMapper customBlogArticleMapper;
    @Resource
    private ContentMapper contentMapper;
    @Resource
    private BlogCategoryMapper categoryMapper;
    
    @Transactional(isolation= Isolation.DEFAULT,propagation= Propagation.REQUIRED,rollbackFor= CustomException.class)
    public void save(BlogArticleVO blogArticleVO) throws Exception {
        BlogArticle blogArticle = new BlogArticle();
        //拷贝属性
        BeanUtils.copyProperties(blogArticleVO, blogArticle);
        blogArticle.setCreateTime(new Date());
        blogArticle.setModifyTime(new Date());
        
        customBlogArticleMapper.insertSelective(blogArticle);
        
        Content content = new Content();
        content.setBid(blogArticle.getId());
        content.setContent(blogArticleVO.getContent());
        //保存文章内容
        contentMapper.insert(content);
        
        //设置文章分类中的articleCount字段
        List<Integer> categoryIds = JSONArray.parseArray(blogArticleVO.getCategoryId(),Integer.class);
        for(Integer id : categoryIds){
            //先查询后修改
            BlogCategory category = categoryMapper.selectByPrimaryKey(id);
            int articleCount = category.getArticleCount() + 1;
            category.setArticleCount(articleCount);
            categoryMapper.updateByPrimaryKey(category);
        }
        
    }
    
    @Override
    public PageResult findDetailByPage(Integer currentPage, Integer pageSize) {
        //将分页设置给分页器
        PageHelper.startPage(currentPage, pageSize);
        //查询
        List<BlogArticle> blogArticleList = blogArticleMapper.selectByExample(null);
        
        List<BlogArticleVO> blogArticleVOList = new ArrayList<>();
       
        //查询商品内容
        for(BlogArticle blogArticle : blogArticleList) {
            Content content = contentMapper.selectByPrimaryKey(blogArticle.getId());
           
            BlogArticleVO blogArticleVO = new BlogArticleVO();
            // 1. 拷贝blogArticle属性到blogArticleVO
            BeanUtils.copyProperties(blogArticle, blogArticleVO);
            // 2. 将文章内容复制到blogArticleVO
            blogArticleVO.setContent(content.getContent());
    
            blogArticleVOList.add(blogArticleVO);
        }
        PageInfo<BlogArticleVO> pageInfo = new PageInfo<BlogArticleVO>(blogArticleVOList);
      
        return new PageResult(pageInfo.getTotal(),pageInfo.getList());
    }
    
    @Override
    public PageResult findByPage(Integer currentPage, Integer pageSize) {
        //将分页设置给分页器
        PageHelper.startPage(currentPage, pageSize);
        //查询
        List<BlogArticle> blogArticleList = blogArticleMapper.selectByExample(null);
       
        PageInfo<BlogArticle> pageInfo = new PageInfo<BlogArticle>(blogArticleList);
    
        return new PageResult(pageInfo.getTotal(),pageInfo.getList());
    }
    
    @Transactional(isolation= Isolation.DEFAULT,propagation= Propagation.REQUIRED,rollbackFor= CustomException.class)
    @Override
    public void update(BlogArticleVO blogArticleVO) {
        BlogArticle blogArticle = new BlogArticle();
        //拷贝属性
        BeanUtils.copyProperties(blogArticleVO, blogArticle);
        blogArticle.setModifyTime(new Date());
        blogArticleMapper.updateByPrimaryKeySelective(blogArticle);
    
        //修改
        Content content = new Content();
        content.setBid(blogArticle.getId());
        content.setContent(blogArticleVO.getContent());
        contentMapper.updateByPrimaryKeySelective(content);
    }
    
    @Override
    public BlogArticleVO getById(Integer id) {
        BlogArticleVO blogArticleVO = new BlogArticleVO();
        //查询出文章内容的字段
        BlogArticle blogArticle = blogArticleMapper.selectByPrimaryKey(id);
        if(blogArticle != null){
            BeanUtils.copyProperties(blogArticle, blogArticleVO);
        }
    
        Content content = contentMapper.selectByPrimaryKey(id);
        blogArticleVO.setContent(content.getContent());
        
        return blogArticleVO;
    }
}
