package com.snapp.admin.cache.cluster;//package com.shining.task.cache.cluster;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import redis.clients.jedis.JedisCluster;
//
///**
// * <p>
// *     redis template
// * </p>
// *
// * @author zw(汤泽辰吃得多喝得多)
// * @date 2017-12-04  11:57
// */
//@Component
//public class RedisTemplate {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(RedisTemplate.class);
//
//    @Autowired
//    private JedisCluster        jedisCluster;
//
//    @Autowired
//    private RedisProperties     redisProperties;
//
//    private static final String PREFIX = "task-";                                     //用于隔开缓存前缀与缓存键值
//
//    /**
//     * 设置缓存
//     * @param key    缓存key
//     * @param value  缓存value
//     */
//    public void set(String key, String value) {
//        jedisCluster.set(PREFIX + key, value);
////        LOGGER.debug("RedisUtil:set cache key={},value={}", PREFIX + key, value);
//    }
//
//    /**
//     * 设置缓存，并且自己指定过期时间
//     * @param key
//     * @param value
//     * @param expireTime 过期时间
//     */
//    public void setWithExpireTime(String key, String value, int expireTime) {
//        jedisCluster.setex(PREFIX + key, expireTime, value);
////        LOGGER.debug("RedisUtil:setWithExpireTime cache key={},value={},expireTime={}", PREFIX + key, value, expireTime);
//    }
//
////    /**
////     * 设置缓存，并且由配置文件指定过期时间
////     * @param prefix
////     * @param key
////     * @param value
////     */
////    public void setWithExpireTime(String prefix, String key, String value) {
////        int EXPIRE_SECONDS = redisProperties.getTimeout();
////        jedisCluster.setex(prefix + PREFIX + key, EXPIRE_SECONDS, value);
////        LOGGER.debug("RedisUtil:setWithExpireTime cache key={},value={},expireTime={}", prefix + PREFIX + key, value, EXPIRE_SECONDS);
////    }
//
//    /**
//     * 获取指定key的缓存
//     * @param prefix
//     * @param key
//     */
//    public String get(String prefix, String key) {
//        String value = jedisCluster.get(prefix + PREFIX + key);
//        LOGGER.debug("RedisUtil:get cache key={},value={}", prefix + PREFIX + key, value);
//        return value;
//    }
//
//    /**
//     * 删除指定key的缓存
//     * @param prefix
//     * @param key
//     */
//    public void deleteWithPrefix(String prefix, String key) {
//        jedisCluster.del(prefix + PREFIX + key);
//        LOGGER.debug("RedisUtil:delete cache key={}", prefix + PREFIX + key);
//    }
//
//    public void delete(String key) {
//        jedisCluster.del(key);
//        LOGGER.debug("RedisUtil:delete cache key={}", key);
//    }
//}
