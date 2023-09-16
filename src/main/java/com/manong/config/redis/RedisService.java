package com.manong.config.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisService
 * @Description: RedisService业务工具类
 * @Author zys
 * @Version 1.0
 * @Date 2023/6/20 16:04
 **/
@Component
public class RedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    //存缓存
    public void set(String key ,String value,Long timeOut){
        redisTemplate.opsForValue().set(key,value,timeOut, TimeUnit.SECONDS);
    }
    //取缓存
    public String get(String key){
        return (String) redisTemplate.opsForValue().get(key);
    }
    //清除缓存
    public void del(String key){
        redisTemplate.delete(key);
    }
}
