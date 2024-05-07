package br.com.rafaoliveira.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        License license = new License().name("Apache 2.0").url("https://github.com/rafaoliveira22/rest-with-spring-boot-and-java");
        Info info = new Info()
                        .title("RESTFul API with Java 21 and Spring Boot 3.2.1")
                        .version("v1")
                        .description("API developed in the course: \"Desenvolva uma API REST do 0 e implante na AWS c. Spring Boot framework Swagger JWT JUnit 5 Mockito Docker React JS e +\"")
                        .termsOfService("https://github.com/rafaoliveira22/rest-with-spring-boot-and-java")
                        .license(license);

        return new OpenAPI().info(info);
    }
}
