package com.coppel.crud_polizas.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
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
                        .title("API de Inventario y Pólizas")
                        .version("1.0")
                        .description("Documentación de la API de Inventario y Pólizas")
                        .contact(new Contact()
                                .name("Diego Otniel Zazueta Aguirre")
                                .email("Diegoztag@gmail.com"))
                )
                .externalDocs(new ExternalDocumentation()
                        .description("Repositorio GitHub")
                        .url("https://github.com/Diegoztag/crud-polizas-backend"));
    }
}
