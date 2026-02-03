package com.example.practice.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    // Swagger UI: http://localhost:8080/swagger-ui.html
    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
                .components(new Components())
                .info(new Info());
    }

    private Info info() {
        return new Info()
                .title("todo list API")
                .description("todo list API 문서입니다")
                .version("1.0");
    }
}
