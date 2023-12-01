package com.mateus.henrique.demoparkapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SpringDocOpenApiConfig {

  @Bean
  public OpenAPI openAPI() {
    return new OpenAPI()
      .info(
        new Info()
          .title("REST API - Spring Park")
          .description("API para gestão de estacionamento de veiculos")
          .version("v1")
          .contact(new Contact().name("Mateus").email("mateushbsb@gmail.com"))
      );
  }
}
