package xyz.guqing.goblin.controller;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import xyz.guqing.goblin.common.CustomException;
import xyz.guqing.goblin.common.PageResult;
import xyz.guqing.goblin.common.Result;
import xyz.guqing.goblin.entity.PersonalLink;
import xyz.guqing.goblin.service.PersonalLinkService;

import javax.annotation.Resource;

@RestController
@RequestMapping("/mylink")
public class PersonalLinkController {
    private static final Logger logger = LoggerFactory.getLogger(PersonalLinkController.class);
    
    @Resource
    private PersonalLinkService personalLinkService;
    
    @PostMapping("/save")
    @Transactional(isolation= Isolation.DEFAULT,propagation= Propagation.REQUIRED,rollbackFor= CustomException.class)
    public Result save(@RequestBody PersonalLink personalLink) {
        try{
            personalLinkService.save(personalLink);
            return Result.success();
        }catch (Exception e){
            logger.error("添加个人社交媒体链接失败，入口参数：{}，错误信息：{}", JSONObject.toJSONString(personalLink), e.getMessage());
            return Result.error();
        }
    }
    
    @GetMapping("/get/{id}")
    public Result getById(@PathVariable("id") Integer id){
        try{
            PersonalLink personalLink = personalLinkService.getById(id);
            return Result.success(personalLink);
        }catch (Exception e){
            logger.error("查询个人社交媒体链接失败，入口参数：{},错误信息：{}", id, e.getMessage());
            return Result.error();
        }
    }
    @GetMapping("/list")
    public PageResult queryAll(Integer currentPage, Integer pageSize){
        try{
            PageResult pageResult = personalLinkService.queryAll(currentPage, pageSize);
            return pageResult;
        }catch (Exception e){
            logger.error("分页查询全部个人社交媒体链接失败，入口参数：{},错误信息：{}", currentPage+", "+pageSize, e.getMessage());
            return new PageResult(null, null);
        }
    }
    
    @PostMapping("/update")
    @Transactional(isolation= Isolation.DEFAULT,propagation= Propagation.REQUIRED,rollbackFor= CustomException.class)
    public Result update(@RequestBody PersonalLink personalLink){
        try{
            personalLinkService.update(personalLink);
            return Result.success();
        }catch (Exception e){
            logger.error("更新个人社交媒体链接失败，入口参数：{}，错误信息：{}", JSONObject.toJSONString(personalLink), e.getMessage());
            return Result.error();
        }
    }
    
    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Integer id){
        try{
            personalLinkService.delete(id);
            return Result.success();
        }catch (Exception e){
            logger.error("删除个人社交媒体链接失败，入口参数：{}，错误信息：{}", id, e.getMessage());
            return Result.error();
        }
    }
}
