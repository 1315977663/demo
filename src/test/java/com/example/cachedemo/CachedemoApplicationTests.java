package com.example.cachedemo;

import com.example.cachedemo.services.CacheServer;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CachedemoApplicationTests {

    @Autowired
    CacheServer cacheServer;

    @Test
    public void contextLoads() {
        cacheServer.putSmsCodeCache("123245");
        try {
            liu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void phoneSmsCode() {
        String code = cacheServer.getSmsCode("1315977663");
        if (code.length() == 6) {
            log.error("【慢点再发吧】");
        } else {
            code = cacheServer.putSmsCodeCache("1315977663");
            log.info("【code】{}", code);
        }
    }

    @Test
    public void login () {
        String code = cacheServer.getSmsCode("1315977663");
        if (code == null) {
            log.error("【请先获取验证码】");
        }
        if (code.equals("123456")) {
            log.error("【登录成功】");
        } else {
            log.error("【验证码错误】");
        }
    }


//    public static void main (String arg[]) {
//        try {
//            liu();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    // io包下流的练习
    public static void liu() throws IOException {
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            bufferedReader = new BufferedReader( // 套上缓冲流 就可以一行一行的读了 readLine
                    new InputStreamReader( // 将字节流装换成字符流
                            new FileInputStream("src/test/java/com/example/cachedemo/aaa.txt")
                    )
            );
            bufferedWriter = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream("src/test/java/com/example/cachedemo/bbb.txt")
                    )
            );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String a = null;
        assert bufferedReader != null;
        while((a = bufferedReader.readLine()) != null) {
           log.info("{}", a);
            assert bufferedWriter != null;
            bufferedWriter.write(a);
            bufferedWriter.write("\r\n");
        }
        assert bufferedWriter != null;
        bufferedWriter.flush();
        bufferedWriter.close();
        bufferedReader.close();
    }
}
