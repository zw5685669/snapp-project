//package com.snapp.admin.core;
//
//import org.springframework.boot.web.servlet.ServletRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
//import org.springframework.web.servlet.DispatcherServlet;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
///**
// * <p>
// *
// * </p>
// *
// * @author zw(汤泽辰吃得多喝得多)
// * @date 2017-12-18  15:13
// */
//@Configuration
//@ComponentScan("com.snapp.admin.controller")
//@EnableWebMvc
//@Component
//public class MvcConfigureRest extends WebMvcConfigurerAdapter {
//
//    @Bean
//    public ServletRegistrationBean restServlet(){
//        //注解扫描上下文
//        AnnotationConfigWebApplicationContext applicationContext
//                = new AnnotationConfigWebApplicationContext();
//        //base package
//        applicationContext.scan("com.snapp.admin");
//        //通过构造函数指定dispatcherServlet的上下文
//        DispatcherServlet rest_dispatcherServlet
//                = new DispatcherServlet(applicationContext);
//
//        //用ServletRegistrationBean包装servlet
//        ServletRegistrationBean registrationBean
//                = new ServletRegistrationBean(rest_dispatcherServlet);
//        registrationBean.setLoadOnStartup(1);
//        //指定urlmapping
//        registrationBean.addUrlMappings("/*");
//        //指定name，如果不指定默认为dispatcherServlet
//        registrationBean.setName("rest");
//        return registrationBean;
//    }
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/**");
//    }
//}
