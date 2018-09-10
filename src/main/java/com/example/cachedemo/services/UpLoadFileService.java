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

    public Boolean upLoadFile (MultipartFile file) {
        log.info("【上传文件名：{}】", file.getOriginalFilename());

        String fileName = file.getOriginalFilename();

        String suffixName = fileName.substring(fileName.lastIndexOf(".")); // 最后一次出现 点的位置

        String basePath = System.getProperty("user.dir");

        String path =  "/upload/";

        fileName = basePath + path + UUID.randomUUID() + suffixName;

        log.info("【文件路径】{}", fileName);

        File fileData = new File(fileName);

        if (!fileData.getParentFile().exists()) {  // 判断文件夹是否存在
            if (fileData.getParentFile().mkdir()) { //  // 穿件文件夹
                log.info("【上传文件夹穿件成功】{}", fileData.getParentFile().getPath());
            } else {
                log.error("【上传文件夹创建失败】");
            }
        }

        try {
            file.transferTo(fileData);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
