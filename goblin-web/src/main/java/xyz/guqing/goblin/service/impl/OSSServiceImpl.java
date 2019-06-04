package xyz.guqing.goblin.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import xyz.guqing.goblin.common.CustomException;
import xyz.guqing.goblin.common.OSSClientUtil;
import xyz.guqing.goblin.service.OSSService;

import javax.annotation.Resource;
import java.io.IOException;
@Service
public class OSSServiceImpl implements OSSService {
    @Resource
    private OSSClientUtil ossClientUtil;
    
    @Override
    public String uploadFile(MultipartFile file) throws IOException, CustomException {
        if (file == null || file.getSize() <= 0) {
            throw new CustomException("文件不能为空");
        }
        String name = ossClientUtil.uploadFile2Oss(file);
        String fileUrl = ossClientUtil.getFileUrl(name);
        return fileUrl;
    }
    
}
