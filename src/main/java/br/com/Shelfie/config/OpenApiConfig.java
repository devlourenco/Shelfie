package br.com.Shelfie.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;


@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Shelfie API",
                version = "1.0",
                description = "API REST para gerenciamento de livros e acompanhamento de leitura."
        )
)
public class OpenApiConfig {
}
