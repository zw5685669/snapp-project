package com.snapp.admin.cache.template;//package com.shining.task.cache.template;//package com.shining.task.cache;
////
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.data.redis.core.RedisTemplate;
////import org.springframework.data.redis.core.StringRedisTemplate;
////import org.springframework.data.redis.core.ValueOperations;
////import org.springframework.stereotype.Component;
////
////import java.util.concurrent.TimeUnit;
////
/////**
//// * <p>
//// *     redis工具类
//// * </p>
//// *
//// * @author zw(汤泽辰吃得多喝得多)
//// * @date 2017-12-13  19:21
//// */
////@Component
////public class RedisClient {
////
////    @Autowired
////    private StringRedisTemplate template;
////
////    @Autowired
////    private RedisTemplate       redisTemplate;
////
////    /**
////     * 设置缓存
////     * @param keyValues
////     * @param time 分钟
////     */
////    public void set(String[] keyValues, int time) {
////        ValueOperations<String, String> ops = template.opsForValue();
////        for (int i = 0; i < keyValues.length; i += 2) {
////            String key = keyValues[i];
////            String value = keyValues[i + 1];
////            ops.set(key, value, time, TimeUnit.MINUTES);//1分钟过期
////        }
////    }
////
////    /**
////     * 设置缓存
////     * @param key
////     * @param value
////     * @param time 分钟
////     */
////    public void set(String key, String value, int time) {
////        ValueOperations<String, String> ops = template.opsForValue();
////        ops.set(key, value, time, TimeUnit.MINUTES);//1分钟过期
////    }
////
////    /**
////     * 获取缓存
////     * @param key
////     * @return
////     */
////    public String get(String key) {
////        ValueOperations<String, String> ops = this.template.opsForValue();
////        return ops.get(key);
////    }
////}
