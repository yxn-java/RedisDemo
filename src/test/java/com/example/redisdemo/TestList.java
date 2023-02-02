package com.example.redisdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

@SpringBootTest
public class TestList {

    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    public void testList() {
        ListOperations<String, String> list = redisTemplate.opsForList();
        // 把数据插入到List的左边
        list.leftPush("list","hello");
        list.leftPush("list","spring");
        list.leftPush("list","boot");
        // 从左边取出List中的数据
        String value=(String)list.leftPop("list");
        System.out.println("list value :"+value.toString());
    }
    @Test
    public void testListRange() {
        ListOperations<String, String> list = redisTemplate.opsForList();
        // 从List的左边插入数据
        list.leftPush("list","weiz");
        list.leftPush("list","spring");
        list.leftPush("list","boot");
        // 调用range()方法获取部分List
        List<String> values=list.range("list",0,2);
        for (String v:values){
            System.out.println("list range :"+v);
        }
    }
}
