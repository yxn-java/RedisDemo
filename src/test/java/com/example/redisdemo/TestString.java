package com.example.redisdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.io.Serializable;
@SpringBootTest//测试类专用注解
public class TestString {
    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    public void testString() {
        ValueOperations<Serializable, Object> operations =  redisTemplate.opsForValue();
        // 调用set()方法创建缓存
        operations.set("string","you");
        // 获取缓存数据
        String value=(String) operations.get("string");
        System.out.println("string value :"+value);
    }
    @Test
    public void testStringIncr() {
        // 设置当前在线用户数
        redisTemplate.opsForValue().set("user:online", "100");
        // 当前在线用户数+1
        redisTemplate.opsForValue().increment("user:online");
        // 获取缓存数据
        Integer value=(Integer) redisTemplate.opsForValue().get("user:online");
        System.out.println("string value :"+value);
    }
}
