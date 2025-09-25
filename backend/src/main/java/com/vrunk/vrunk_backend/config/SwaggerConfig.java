package com.vrunk.vrunk_backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API REST Documentation")
                        .description("API REST pour l'application Vrunk")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Vrunk Team")
                                .url("https://vrunk.com")
                                .email("support@vrunk.com")));
    }
}
