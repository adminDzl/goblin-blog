package xyz.guqing.goblin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import xyz.guqing.goblin.common.CustomException;
import xyz.guqing.goblin.common.Result;
import xyz.guqing.goblin.service.OSSService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/oss")
public class OSSController {
    private static final Logger logger = LoggerFactory.getLogger(OSSController.class);
    @Resource
    private OSSService ossService;
    
    @PostMapping("/upload_pic")
    public Result headImgUpload(MultipartFile image, HttpServletRequest request) {
        try {
            String pictureUrl = ossService.uploadFile(image);
            //返回图片url地址
            return Result.success(pictureUrl);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("上传图片错误，流对象文件名称：{}",image.getOriginalFilename());
            return Result.error(e.getMessage());
        }
    }
    
    @PostMapping("/upload_file")
    public Result handlerFileUpload(MultipartFile multiFile, HttpServletRequest request) {
        try {
            System.out.println("上传文件执行。。。。。");
            String fileUrl = ossService.uploadFile(multiFile);
            //返回图片url地址
            return Result.success(fileUrl);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("上传文件错误，流对象文件名称：{}",multiFile.getOriginalFilename());
            return Result.error(e.getMessage());
        }
    }
}
