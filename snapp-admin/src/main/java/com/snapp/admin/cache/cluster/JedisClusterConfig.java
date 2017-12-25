package com.snapp.admin.cache.cluster;//package com.shining.task.cache.cluster;
//
//import java.util.HashSet;
//import java.util.Set;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import redis.clients.jedis.HostAndPort;
//import redis.clients.jedis.JedisCluster;
//
///**
// * <p>
// *     生成JedisCluster对象
// * </p>
// *
// * @author zw(汤泽辰吃得多喝得多)
// * @date 2017-12-13  17:30
// */
//@Configuration
//public class JedisClusterConfig {
//
//    @Autowired
//    private RedisProperties redisProperties;
//
//    /**
//     * 注意：
//     * 这里返回的JedisCluster是单例的，并且可以直接注入到其他类中去使用
//     * @return
//     */
//    @Bean
//    public JedisCluster getJedisCluster() {
//        String[] serverArray = StringUtils.split(redisProperties.getNodes(), ",");//获取服务器数组
//        Set<HostAndPort> nodes = new HashSet<>();
//
//        for (String ipPort : serverArray) {
//            String[] ipPortPair = StringUtils.split(ipPort, ":");
//            nodes.add(new HostAndPort(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim())));
//        }
//
//        return new JedisCluster(nodes, redisProperties.getConnectionTimeout(), redisProperties.getSoTimeout(), redisProperties.getMaxAttempts(),
//                redisProperties.getPassword(), redisProperties.getPool());
//    }
//
//}