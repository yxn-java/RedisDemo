package com.example.redisdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class TestHash {

    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    public void testHash() {
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        // 调用put()方法创建Hash数据缓存
        hash.put("hash","test","hello");
        hash.put("hash","test","spring");
        hash.put("hash","test","boot");
        // 获取Hash数据
        String value=(String) hash.get("hash","test");
        System.out.println("hash value :"+value);
    }
}
