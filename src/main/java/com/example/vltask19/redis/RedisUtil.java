package com.example.vltask19.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    //    redisTemplate.opsForValue();//操作字符串
    public String getString(String key) {
        try {
            Object result = key == null ? null : redisTemplate.opsForValue().get(key);
            return result == null ? null : result.toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void setString(String key, String value,Integer expiryMilliseconds) {
        try {
            redisTemplate.opsForValue().set(key, value,expiryMilliseconds, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    //    redisTemplate.opsForHash();//操作hash
    //    redisTemplate.opsForList();//操作list
    //    redisTemplate.opsForSet();//操作set
    //    redisTemplate.opsForZSet();//操作有序set
}
