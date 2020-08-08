package com.labforward.api.eln.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * SpringFox SwaggerUI v3 configuration, adapted from:
 * https://github.com/springfox/springfox
 */
@Configuration
@EnableSwagger2
@EnableOpenApi
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(regex(".*/api.*"))
                .build();
    }

    private ApiInfo apiInfo() {
        //TODO(yigitalp): Get from properties file
        return new ApiInfo(
                "Labforward ELN Entry API",
                "Rest API for CRUD and search operations on Notebook Entries.",
                "1.0.0",
                null,
                new Contact("Yiğitalp Ertem", "https://www.labforward.io/", "yalpertem@gmail.com"),
                "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0",
                Collections.emptyList());
    }
}