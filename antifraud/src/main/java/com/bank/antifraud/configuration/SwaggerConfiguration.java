package com.bank.antifraud.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author ivanvitsin
 *   Кофигурационный класс для Swagger, инструмента для создания документации по API.
 * Аннотация @EnableSwagger2 необходима для включения модулем поддержки swagger doc.
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {


    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("src/main/java/com/bank/antifraud"))
                .paths(PathSelectors.any())
                .build().host("http://localhost:8086/api/anti-fraud");

    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("bank API")
                .version("1.0.0")
                .contact(new Contact()
                        .email("me@upagge.ru")
                        .url("https://uPagge.ru")
                        .name("Vitsin Ivan")
                )
        );
    }
}
