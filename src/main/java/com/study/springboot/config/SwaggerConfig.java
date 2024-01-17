package com.study.springboot.config;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;


@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
@Configuration
@OpenAPIDefinition
public class SwaggerConfig {
 
    @Bean
    public OpenAPI api(){
        Info info = new Info().title("").version("v3").description("aaa");
         return new OpenAPI().components(new Components()).info(info);
    }
   
}