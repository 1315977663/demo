package com.example.cachedemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CachedemoApplicationTests {

    @Autowired
    CacheServer cacheServer;

    @Test
    public void contextLoads() {
        cacheServer.setSmsCode("123245");
    }

    @Cacheable(value = "product", keyGenerator = "myGenerator")
    public String setCache(String value) {
        return value;
    }
}
