package com.example.swaggerapp;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {                                    
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
          .select()                                  
          .apis(RequestHandlerSelectors.withClassAnnotation(Api.class)) // dokumentuje se vse s anotaci @Api
          .paths(PathSelectors.any())
          .build();                                           
    }

    // http://localhost:8080/v2/api-docs
    // http://localhost:8080/swagger-ui.html
}