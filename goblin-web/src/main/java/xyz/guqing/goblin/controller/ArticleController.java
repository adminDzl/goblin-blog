package xyz.guqing.goblin.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import xyz.guqing.goblin.common.PageResult;
import xyz.guqing.goblin.common.Result;
import xyz.guqing.goblin.entity.BlogArticleVO;
import xyz.guqing.goblin.service.ArticleService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/article")
public class ArticleController {
    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);
    
    @Resource
    private ArticleService articleService;
    
    @PostMapping("/save")
    public Result save(@RequestBody BlogArticleVO blogArticleVO , HttpServletRequest request){
        //获取客户就的ip地址
        String ip = request.getRemoteAddr();
        blogArticleVO.setIp(ip);
        try{
            System.out.println(JSONObject.toJSONString(blogArticleVO));
            articleService.save(blogArticleVO);
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            logger.error("保存文章出错，入口参数为:{}，错误信息", JSONObject.toJSONString(blogArticleVO),e.getMessage());
            return Result.error();
        }
    }
    
    /**
     * 分页查询文章内容
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping("/list_detail")
    public PageResult findDetailByPage(Integer currentPage, Integer pageSize){
        PageResult pageResult = null;
        try {
            pageResult = articleService.findDetailByPage(currentPage, pageSize);
            return pageResult;
        }catch (Exception e){
            logger.error("查询文章内容详情出错，入口参数为:{}，查询结果为：{},错误信息为:{}", currentPage+","+pageSize, JSONArray.toJSONString(pageResult), e.getMessage());
            return new PageResult(null,null);
        }
        
    }
    
    @GetMapping("/list")
    public PageResult findByPage(Integer currentPage, Integer pageSize){
        PageResult pageResult = null;
        try {
            pageResult = articleService.findByPage(currentPage, pageSize);
            return pageResult;
        }catch (Exception e){
            logger.error("查询文章出错，入口参数为:{}，查询结果为：{},错误信息为:{}", currentPage+","+pageSize, JSONArray.toJSONString(pageResult), e.getMessage());
            return new PageResult(null,null);
        }
        
    }
    
    /**
     * 更新文章
     * @param blogArticleVO 完整文章对象属性VO
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody BlogArticleVO blogArticleVO){
        try{
            articleService.update(blogArticleVO);
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            logger.error("修改文章出错，入口参数为:{}，错误信息", JSONObject.toJSONString(blogArticleVO),e.getMessage());
            return Result.error();
        }
    }
    
    @GetMapping("/get/{id}")
    public BlogArticleVO getById(@PathVariable("id") Integer id){
        BlogArticleVO blogArticleVO = null;
        try{
            blogArticleVO = articleService.getById(id);
            return blogArticleVO;
        }catch (Exception e){
            e.printStackTrace();
            logger.error("获取文章出错，入口参数为:{}，错误信息", JSONObject.toJSONString(blogArticleVO),e.getMessage());
            return new BlogArticleVO();
        }
    }
}
