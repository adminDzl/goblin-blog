package xyz.guqing.goblin.controller;

import com.alibaba.fastjson.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import xyz.guqing.goblin.common.Result;
import xyz.guqing.goblin.entity.BlogCategory;
import xyz.guqing.goblin.service.CategoryService;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
    @Resource
    private CategoryService categoryService;
    
    @PostMapping("/save")
    public Result save(@RequestBody BlogCategory category){
        try{
            categoryService.save(category);
            return Result.success();
        }catch (Exception e){
            logger.error("新增文章分类出错,错误信息：{}", e.getMessage());
            return Result.error();
        }
    }
    
    @GetMapping("/list")
    public List<BlogCategory> findAll(){
        try{
            return categoryService.findAll();
        }catch (Exception e){
            logger.error("查询文章分类出错，方法名称findAll,错误信息：{}", e.getMessage());
            return Collections.emptyList();
        }
    }
    
    @PostMapping("/update")
    public Result update(@RequestBody BlogCategory category){
        try{
            categoryService.update(category);
            return Result.success();
        }catch (Exception e){
            logger.error("更新文章分类出错，错误信息：{}", e.getMessage());
            return Result.error();
        }
    }
    
    @PostMapping("/move_up/{id}")
    public Result moveUp(@PathVariable("id") int id){
        try{
            categoryService.moveUp(id);
            return Result.success();
        }catch (Exception e){
            logger.error("分类上移出错，错误信息：{}", e.getMessage());
            return Result.error();
        }
    }
    
    @PostMapping("/move_down/{id}")
    public Result moveDown(@PathVariable("id") int id){
        try{
            categoryService.moveDown(id);
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            logger.error("分类下移出错，错误信息：{}", e.getMessage());
            return Result.error();
        }
    }
    
    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Integer id){
        try{
            categoryService.delete(id);
            return Result.success();
        }catch (Exception e){
            logger.error("删除分类错误，错误信息：{}", e.getMessage());
            return Result.error();
        }
    }
}
