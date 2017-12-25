package com.snapp.admin.cache.cluster;//package com.shining.task.cache.cluster;
//
//import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//
///**
// * <p>
// *     redis集群配置文件
// * </p>
// *
// * @author zw(汤泽辰吃得多喝得多)
// * @date 2017-12-13  17:26
// */
//@Configuration
//@Component
//@ConfigurationProperties(prefix = "redis")
//public class RedisProperties {
//
//    /**
//     * redis集群节点
//     *
//     */
//    private String                  nodes;
//
//    /**
//     * 密码
//     */
//    private String                  password;
//
//    /**
//     * 配置 pool
//     */
//    private GenericObjectPoolConfig pool;
//
//    /**
//     * 连接超时
//     */
//    private int                     connectionTimeout;
//
//    /**
//     * 读取超时
//     */
//    private int                     soTimeout;
//
//    /**
//     * 重试
//     */
//    private int                     maxAttempts;
//
//    public String getNodes() {
//        return nodes;
//    }
//
//    public void setNodes(String nodes) {
//        this.nodes = nodes;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public GenericObjectPoolConfig getPool() {
//        return pool;
//    }
//
//    public void setPool(GenericObjectPoolConfig pool) {
//        this.pool = pool;
//    }
//
//    public int getConnectionTimeout() {
//        return connectionTimeout;
//    }
//
//    public void setConnectionTimeout(int connectionTimeout) {
//        this.connectionTimeout = connectionTimeout;
//    }
//
//    public int getSoTimeout() {
//        return soTimeout;
//    }
//
//    public void setSoTimeout(int soTimeout) {
//        this.soTimeout = soTimeout;
//    }
//
//    public int getMaxAttempts() {
//        return maxAttempts;
//    }
//
//    public void setMaxAttempts(int maxAttempts) {
//        this.maxAttempts = maxAttempts;
//    }
//}
