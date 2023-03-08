package src.main.java.com.bank.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Класс конфигурации для Swagger, инструмента для создания документации
 * @author Nataliya
 * @version 1.0
 * @since 20.02.2023
 */

@Configuration
public class HistoryConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .host("http://http://localhost:8088/api/history/")
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("Bank API")
                .version("1.0.0")
                .contact(new Contact()
                        .email("me@upagge.ru")
                        .url("https://uPagge.ru")
                        .name("Nataliya")
                )
        );
    }
}


