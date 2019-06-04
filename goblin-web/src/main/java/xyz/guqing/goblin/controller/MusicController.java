package xyz.guqing.goblin.controller;

import com.alibaba.fastjson.JSONObject;
import net.sf.jsqlparser.statement.select.ExceptOp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import xyz.guqing.goblin.common.PageResult;
import xyz.guqing.goblin.common.Result;
import xyz.guqing.goblin.entity.Music;
import xyz.guqing.goblin.service.MusicService;

import javax.annotation.Resource;

@RestController
@RequestMapping("/music")
public class MusicController {
    private static final Logger logger = LoggerFactory.getLogger(MusicController.class);
    
    @Resource
    private MusicService musicService;
    
    @PostMapping("/save")
    public Result save(@RequestBody Music music) {
        try {
            System.out.println("保存音乐执行。。。。。");
            musicService.save(music);
            return Result.success();
        } catch (Exception e){
            logger.error("添加音乐出错，入口参数：{},错误信息：{}", JSONObject.toJSONString(music), e.getMessage());
            return Result.error();
        }
    }
    
    @GetMapping("/list")
    public PageResult findAllByPage(Integer currentPage, Integer pageSize) {
        try {
            PageResult pageResult = musicService.findAllByPage(currentPage, pageSize);
            return pageResult;
        } catch (Exception e) {
            logger.error("分页查询音乐出错，入口参数：{},错误信息：{}", currentPage + ", " + pageSize, e.getMessage());
            return new PageResult(null, null);
        }
    }
    
    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Integer id) {
        try {
            musicService.delete(id);
            return Result.success();
        } catch (Exception e) {
            logger.error("删除音乐出错，入口参数：{},错误信息：{}", id, e.getMessage());
            return Result.error();
        }
    }
    
    @PostMapping("/update")
    public Result delete(@RequestBody Music music) {
        try {
            musicService.update(music);
            return Result.success();
        } catch (Exception e) {
            logger.error("删除音乐出错，入口参数：{},错误信息：{}", JSONObject.toJSONString(music), e.getMessage());
            return Result.error();
        }
    }
    
    @GetMapping("/get/{id}")
    public Result getById(@PathVariable("id") Integer id){
        try {
            Music music = musicService.getById(id);
            return Result.success(music);
        } catch (Exception e){
            logger.error("根据id查询音乐信息出错，入口参数：{},错误信息：{}", id, e.getMessage());
            return Result.error();
        }
    }
}
