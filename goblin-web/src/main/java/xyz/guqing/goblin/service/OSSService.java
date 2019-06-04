package xyz.guqing.goblin.service;

import org.springframework.web.multipart.MultipartFile;
import xyz.guqing.goblin.common.CustomException;

import java.io.IOException;

public interface OSSService {
    public String uploadFile(MultipartFile file) throws IOException, CustomException;
}
