package com.example.cachedemo;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CacheServer {

    @Cacheable(value = "smsCode", keyGenerator = "myGenerator")
    public String setSmsCode(String value) {
        return value;
    }
}
