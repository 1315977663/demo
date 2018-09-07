package com.example.cachedemo.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @program: cachedemo
 * @description: 文件上传服务
 * @author: fbl
 * @create: 2018-09-07 10:19
 **/
@Slf4j
@Service
public class UpLoadFileService {
    public String upLoadFile (MultipartFile file) {
        log.info("【上传文件名：{}】", file.getOriginalFilename());

        String fileName = file.getOriginalFilename();

        String suffixName = fileName.substring(fileName.lastIndexOf("."));

        String basePath = System.getProperty("user.dir");

        String path =  "/upload/";

        fileName = basePath + path + UUID.randomUUID() + fileName;

        log.info("文件目录{}", fileName);

        File fileData = new File(fileName);

        if (!fileData.getParentFile().exists()) {
            fileData.getParentFile().mkdir();
        }

        try {
            file.transferTo(fileData);
            return fileName;
        } catch (IOException e) {
            e.printStackTrace();
        }

        fileName = null;
        return fileName;
    }
}
