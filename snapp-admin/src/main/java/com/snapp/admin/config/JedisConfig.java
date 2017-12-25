//package com.snapp.admin.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//
//@Configuration
////@ConfigurationProperties(prefix = JedisConfig.JEDIS_PREFIX )
//public class JedisConfig {
//
//    public static final String JEDIS_PREFIX = "jedis";
//
//    @Bean(name= "jedisPool")
//    @Autowired
//    public JedisPool jedisPool(@Qualifier("jedisPoolConfig") JedisPoolConfig config,
//                                   @Value("${mapper.jedis.pool.host}")String host,
//                                   @Value("${mapper.jedis.pool.port}")int port,
//                                   @Value("${mapper.jedis.pool.timeout}")int timeout,
//                                   @Value("${mapper.jedis.pool.password}")String password) {
//            return new JedisPool(config, host, port,timeout,password);
//    }
//
//    @Bean(name= "jedisPoolConfig")
//    public JedisPoolConfig jedisPoolConfig (@Value("${mapper.jedis.pool.config.maxTotal}")int maxTotal,
//                                                @Value("${mapper.jedis.pool.config.maxIdle}")int maxIdle,
//                                                @Value("${mapper.jedis.pool.config.maxWaitMillis}")int maxWaitMillis) {
//            JedisPoolConfig config = new JedisPoolConfig();
//            config.setMaxTotal(maxTotal);
//            config.setMaxIdle(maxIdle);
//            config.setMaxWaitMillis(maxWaitMillis);
//            return config;
//        }
//
//
//}
