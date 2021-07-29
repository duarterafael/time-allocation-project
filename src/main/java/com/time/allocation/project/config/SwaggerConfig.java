package com.time.allocation.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import com.time.allocation.project.TimeAllocationProjectApplication;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage(TimeAllocationProjectApplication.class.getPackage().getName()))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, Collections.emptyList())
                .globalResponseMessage(RequestMethod.HEAD, Collections.emptyList())
                .globalResponseMessage(RequestMethod.POST, Collections.emptyList())
                .globalResponseMessage(RequestMethod.PUT, Collections.emptyList())
                .globalResponseMessage(RequestMethod.PATCH, Collections.emptyList())
                .globalResponseMessage(RequestMethod.DELETE, Collections.emptyList())
                .globalResponseMessage(RequestMethod.OPTIONS, Collections.emptyList())
                .globalResponseMessage(RequestMethod.TRACE, Collections.emptyList())
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("Time Allocation Project")
                .description("Time Allocation Project Rest Server")
                .version("0.0.1-SNAPSHOT")
                .build();
    }
}
