package com.weitao.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/test")
public class redisController {

    @Autowired
    StringRedisTemplate redisTemplate;


    /**
     * 普通缓存获取
     *
     * @param key 键
     * @return 值
     */
    @GetMapping("/get/{key}")
    public Object get(@PathVariable String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 普通缓存放入
     *
     * @param key   键
     * @param value 值
     * @return true成功 false失败
     */
    @RequestMapping("/set/{key}/{value}")
    public boolean set(@PathVariable String key, @PathVariable String value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

}
