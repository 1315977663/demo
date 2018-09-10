package com.example.cachedemo.webconfig;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * @program: cachedemo
 * @description:
 * @author: fbl
 * @create: 2018-09-07 11:28
 **/
@Configuration
@Slf4j
public class WebConfiguration implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String basePath = System.getProperty("user.dir"); // 获取程序所在的目录
        log.info("【程序目录】{}", basePath);
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/upload/**").addResourceLocations("file:"+basePath+"/upload/"); // 配置静态文件目录，可以通过url直接访问
    }
}
