package com.example.redisdemo;

import com.example.redisdemo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

@SpringBootTest
public class TestRedisTemplate {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testString() {
        // 调用set()方法创建缓存
        redisTemplate.opsForValue().set("hello:redis", "hello spring boot");
        System.out.println("hello redis: "+ redisTemplate.opsForValue().get("hello:redis"));
    }
    //新增指定数据
    @Test
    public void testObj(){
        User user=new User();
        user.setName("weiz");
        user.setPassword("123456");
        user.setAge(30);
        ValueOperations<String, User> operations=redisTemplate.opsForValue();
        // 调用set()方法创建缓存
        operations.set("user:weiz", user);
        // 调用get()方法获取数据
        User u=operations.get("user:weiz");
        System.out.println("name: "+u.getName()+",u.age:"+u.getAge());
    }
    //删除指定数据
    @Test
    public void testDelete() {
        ValueOperations<String, User> operations=redisTemplate.opsForValue();
        redisTemplate.opsForValue().set("weiz:deletekey", "need delete");
        // 删除缓存
        redisTemplate.delete("deletekey");
        // 判断key是否存在
        boolean exists=redisTemplate.hasKey("deletekey");
        if(exists){
            System.out.println("exists is true");
        }else{
            System.out.println("exists is false");
        }
    }
    //测试缓存是否过期
    @Test
    public void testExpire() throws InterruptedException {
        User user=new User();
        user.setName("weiz expire");
        user.setAge(30);
        ValueOperations<String, User> operations=redisTemplate.opsForValue();
        // 创建缓存并设置缓存失效时间
        operations.set("weiz:expire", user,10000, TimeUnit.MILLISECONDS);
        Thread.sleep(5000);
        // 10秒后判断缓存是否存在
        boolean exists=redisTemplate.hasKey("weiz:expire");
        if(exists){
            System.out.println("exists is true");
        }else{
            System.out.println("exists is false");
        }
        Thread.sleep(10000);
        // 10秒后判断缓存是否存在
        exists=redisTemplate.hasKey("weiz:expire");
        if(exists){
            System.out.println("exists is true");
        }else{
            System.out.println("exists is false");
        }
    }
}
