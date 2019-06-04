package xyz.guqing.goblin.controller;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.guqing.goblin.common.PageResult;
import xyz.guqing.goblin.common.Result;
import xyz.guqing.goblin.entity.Microblog;
import xyz.guqing.goblin.service.MicroBlogService;

import javax.annotation.Resource;
import java.util.List;

/**
 * 动态Controller
 */
@RestController
@RequestMapping("/microblog")
public class MicroBlogController {
    private static final Logger logger = LoggerFactory.getLogger(MicroBlogController.class);
    @Resource
    private MicroBlogService microBlogService;
    
    @PostMapping("/save")
    public Result save(Microblog microblog) {
        try{
            microBlogService.save(microblog);
            return Result .success();
        }catch (Exception e){
            logger.error("新增微博（动态）出错，入口参数：{},异常信息：{}", JSONObject.toJSONString(microblog), e.getMessage());
            return Result .error();
        }
    }
    
    @GetMapping("/query_list")
    public PageResult queryAll(Integer currentPage, Integer pageSize) {
        try{
            PageResult pageResult = microBlogService.queryAll(currentPage, pageSize);
            return pageResult;
        }catch (Exception e){
            logger.error("分页查询微博（动态）出错，入口参数：{},异常信息：{}", currentPage+", "+pageSize, e.getMessage());
            return new PageResult(null,null);
        }
    }
}
