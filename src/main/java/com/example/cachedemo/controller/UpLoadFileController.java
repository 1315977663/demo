package com.example.cachedemo.controller;

import com.example.cachedemo.services.UpLoadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @program: cachedemo
 * @description: 文件上传
 * @author: fbl
 * @create: 2018-09-07 10:36
 **/
@RestController
public class UpLoadFileController {

    @Autowired
    UpLoadFileService upLoadFileService;

    @PostMapping("/upload")
    public String upLoad (@RequestParam("file") MultipartFile multipartFile) {
        String msg;
        if ((msg = upLoadFileService.upLoadFile(multipartFile)) != null) {
            return msg;
        } else {
            return "失败";
        }
    }
}
