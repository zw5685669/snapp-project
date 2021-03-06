package com.snapp;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 *     主类
 * </p>
 *
 * @author zw(汤泽辰吃得多喝得多)
 * @date 2017-12-01  19:04
 */
//@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class,
//        MybatisAutoConfiguration.class })
@SpringBootApplication
@ServletComponentScan
@EnableScheduling
@EnableTransactionManagement
@EnableAsync
@RestController
@ComponentScan(basePackages={"com.snapp.common","com.snapp.job"})  //扫描子模块包
public class JobApplication {

    private static final Logger LOGGER = LogManager.getLogger(JobApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(JobApplication.class, args);
    }

    @RequestMapping("/")
    public String set(String key, String value) throws Exception {
        return "start success";
    }
}
