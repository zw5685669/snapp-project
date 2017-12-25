package com.snapp.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * <p>
 *     swagger 配置类
 * </p>
 *
 * @author zw(汤泽辰吃得多喝得多)
 * @date 2017-12-25  19:04
 */
@Configuration
@EnableSwagger2
@EnableWebMvc
public class SwaggerConfiguration {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("")
                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.snapp.admin.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("snapp-api")
                .description("snapp-通用API文档")
                .termsOfServiceUrl("http://139.196.127.16:8080/")
                .license("apache 2.0")
                .version("1.0")
                .build();
    }
}
