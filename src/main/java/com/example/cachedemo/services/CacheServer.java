package com.example.cachedemo.services;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CacheServer {

    @CachePut(value = "smsCode", keyGenerator = "smsKeyGenerator")
    public String putSmsCodeCache(String phone) {
        Random random = new Random();
        int random6 = random.nextInt(900000) + 100000;
        return String.valueOf(random6);
    }

    @Cacheable(value = "smsCode", keyGenerator = "smsKeyGenerator")
    public String getSmsCode (String phone){
        return ""; // 如果没有缓存 则返回 ""
    }
}
