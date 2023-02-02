package com.example.redisdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;

import java.util.Set;

@SpringBootTest
public class TestSet {
    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    public void testSet() {
        String key = "set";
        SetOperations<String, String> set = redisTemplate.opsForSet();
        // 在Set中插入数据
        set.add(key, "hello");
        set.add(key, "spring");
        set.add(key, "boot");
        set.add(key, "hello");

        // 调用members()方法判断某个数据
        Set<String> values = set.members(key);
        for (String v : values) {
            System.out.println("set value :" + v);
        }
    }
    @Test
    public void testSetUnion() {
        SetOperations<String, String> set = redisTemplate.opsForSet();
        // 在seta中插入数据
        set.add("set:a","spring");
        set.add("set:a","weiz");
        set.add("set:a","test");
        // 在setb中插入数据
        set.add("set:b","spring");
        set.add("set:b","weiz");
        set.add("set:b","test");

        // 返回多个集合的并集
        set.union("set:a", "set:b");
        // 调用members()方法判断某个数据
        Set<String> values = set.members("set:a");
        for (String v : values) {
            System.out.println("set value :" + v);
        }
        // 返回多个集合的交集
        //redisTemplate.opsForSet().intersect("set:a", "set:b");
        // 返回集合key1中存在但是key2中不存在的数据集合，即差集
        //redisTemplate.opsForSet().difference("set:a", "set:b");
    }
}
